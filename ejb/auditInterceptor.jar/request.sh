#!/bin/bash

curl -v -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/auditInterceptor/TestEjb
