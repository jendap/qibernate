#!/usr/bin/evn bash

# check the server is running (or advice how to start it)
if ! curl 'http://localhost:8080/services/' 2>&1 >/dev/null; then
  echo "ERROR: Server is not running!"
  echo "You may start it with:"
  # starts war using maven-jetty-plugin
  echo "mvn -am -pl qibernate-cxf verify -P run-war -DskipTests"
  exit 1
fi




# list services
curl -v 'http://localhost:8080/services/'



# send soap service
curl -v -X POST -H 'Content-Type: application/soap+xml; charset=utf-8' -d @newCat11.xml 'http://localhost:8080/services/catServiceWS'



# tests rest service
curl -v -X POST -H 'Content-Type: application/json' -d '{"name": "foo", "age": 42}' 'http://localhost:8080/services/catServiceRS/catservice/new'
curl -v 'http://localhost:8080/services/catServiceRS/catservice/byage?from=18&to=64'
curl -v 'http://localhost:8080/services/catServiceRS/catservice/cat/foo'



# benchmark
#times bash -c "for i in `seq 1 100`; do curl -v 'http://localhost:8080/services/catServiceRS/catservice/byage?from=18&to=64' 2>&1 >/dev/null; done"
