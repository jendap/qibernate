#!/usr/bin/evn bash

# list services
curl -v 'http://localhost:8080/services/'



# send soap request
curl -v -X POST -H 'Content-Type: application/soap+xml; charset=utf-8' -d @newCat11.xml http://localhost:8080/services/catServiceWS/



# tests RESTful service
curl -v -X POST -H 'Content-Type: application/json' -d '{"name": "foo", "age": 5}' 'http://localhost:8080/services/catServiceRS/catservice/new'
curl -v 'http://localhost:8080/services/catServiceRS/catservice/byage?from=1&to=10'
curl -v 'http://localhost:8080/services/catServiceRS/catservice/cat/foo'



# benchmark
times bash -c "for i in `seq 1 100`; do curl -v 'http://localhost:8080/services/catServiceRS/catservice/byage?from=1&to=10' 2>&1 >/dev/null; done"
