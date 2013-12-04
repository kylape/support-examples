#!/bin/bash

request=`sed -e "s/\\$1/$1/" request.xml`
curl -s -H "Content-Type: text/xml" -d "$request" http://localhost:8080/customValidation/hello
echo
