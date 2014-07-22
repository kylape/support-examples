#!/bin/bash

curl -v -H "Content-Type: text/xml" -d "`cat client.xml`" http://localhost:8080/cxfTransform/clientEndpoint
