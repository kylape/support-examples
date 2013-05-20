#!/bin/bash

echo "Saying hello..."
echo
curl -s -H "Content-Type: text/xml" -d "`cat hello.xml`" http://localhost:8080/twoEndpointsCxfSpring/hello | xmllint --format -
echo
echo "Saying goodbye..."
echo
curl -s -H "Content-Type: text/xml" -d "`cat goodbye.xml`" http://localhost:8080/twoEndpointsCxfSpring/goodbye | xmllint --format -
