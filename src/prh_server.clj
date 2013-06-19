(ns prh-server
  (:require [clojure.data.json :as json]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [taoensso.timbre :as t]))

(defn init
  "Function to call when web server is initialized."
  []
  (t/set-level! :info))

(defn on-commit
  "Invoked with a Clojure data structure containing the commit data
  from GitHub. See https://help.github.com/articles/post-receive-hooks for its exact form."
  [commit-data]
  (t/info commit-data)
  ;; Your code here... do something with the data!
  )

(def handler
  "Ring handler function for all requests."
  (params/wrap-params
   (fn
     [req]
     (t/debug req)
     (try
       (if-let [payload (-> req :params (get "payload"))]
         (on-commit (json/read-str payload))
         (t/warn "Request did not contain a 'payload' parameter"))
       (catch Exception e
         (t/error e)))
     {:body "Thanks, Github!"})))

(defn -main [& args]
  (let [port (Integer. (System/getenv "PORT"))]
    (jetty/run-jetty handler {:port port})))