# prh-server

A simple template for a server that can receive Github post-receive hook
POST calls.

## Usage

`lein ring server-headless <port>` to start an embedded Jetty.

To compile a WAR file for use in an existing JVM container, use `lein ring uberwar`

## License

Copyright © 2013 Luke VanderHart

Distributed under the Eclipse Public License, the same as Clojure.
