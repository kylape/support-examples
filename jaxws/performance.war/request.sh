#!/bin/bash

echo Warming up the Hello endpoint
curl -s -H "Content-Type: text/xml" -d "`cat hello.xml`" http://localhost:8080/performance/hello > /dev/null

if [ "$1" == "test" ]; then
  request=`cat test.xml`
else
  request=`sed -e "s/\\$1/$1/" request.xml`
fi

echo Running the test...
curl -s -H "Content-Type: text/xml" -d "$request" http://localhost:8080/performance/clientEndpoint
