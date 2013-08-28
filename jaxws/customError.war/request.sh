#!/bin/bash

# echo "Authorization: Basic `echo 'klape:RedHat13#' | base64`" 
curl -s -u "klape:RedHat13#" -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/customError/hello
