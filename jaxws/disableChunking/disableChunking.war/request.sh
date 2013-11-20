#!/bin/bash

curl -s -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/disableChunking/clientEndpoint
