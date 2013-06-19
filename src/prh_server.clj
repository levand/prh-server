(ns prh-server
  (:require [clojure.data.json :as json]
            [ring.middleware.params :as r]
            [taoensso.timbre :as t]))

(defn init
  "Function to call when web server is initialized."
  []
  (t/set-level! :info)
  (t/set-config! [:appenders :standard-out :enabled?] false)
  (t/set-config! [:appenders :spit :enabled?] true)
  (t/set-config! [:shared-appender-config :spit-filename] "prh-server.log"))

(defn on-commit
  "Invoked with a Clojure data structure containing the commit data
  from GitHub. See https://help.github.com/articles/post-receive-hooks for its exact form."
  [commit-data]
  (t/info commit-data)
  ;; Your code here... do something with the data!
  )

(def handler
  "Ring handler function for all requests."
  (r/wrap-params
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