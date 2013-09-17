#!/bin/bash

if [ "$1" == "-b" ]; then
  req="bad.xml"
else
  req="request.xml"
fi

curl -i -H "Content-Type: text/xml" -d "`cat $req`" http://localhost:8080/invalidParams/hello
