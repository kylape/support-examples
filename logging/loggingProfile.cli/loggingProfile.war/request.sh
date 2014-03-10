#!/bin/bash

curl -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/loggingProfile/LoggingTest
echo
