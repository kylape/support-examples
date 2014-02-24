#!/bin/bash

curl -i -H 'SOAPAction: "hello"' -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/emptySoapAction/HelloWS
