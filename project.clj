(defproject prh-server "0.1.0-SNAPSHOT"
  :description "Template for a simple ring server to accept
  post-receive hook HTTP requests from GitHub."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.5"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.2"]
                 [ring "1.2.0-RC1"]
                 [com.taoensso/timbre "2.1.2"]]
  :ring {:init prh-server/init
         :handler prh-server/handler})
