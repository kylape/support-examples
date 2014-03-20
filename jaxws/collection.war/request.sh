#!/bin/bash

echo --------------------------------
curl -s -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/collection/HelloWS | xmllint --format -
echo --------------------------------
curl -s -H "Content-Type: text/xml" -d "`cat request2.xml`" http://localhost:8080/collection/HelloWS | xmllint --format -
echo --------------------------------
curl -s -H "Content-Type: text/xml" -d "`cat request3.xml`" http://localhost:8080/collection/HelloWS | xmllint --format -
echo --------------------------------
curl -s -H "Content-Type: text/xml" -d "`cat request4.xml`" http://localhost:8080/collection/HelloWS | xmllint --format -
echo --------------------------------
