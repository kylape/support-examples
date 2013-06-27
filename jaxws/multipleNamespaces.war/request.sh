#!/bin/bash

curl -i -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/multipleNamespaces/clientEndpoint
