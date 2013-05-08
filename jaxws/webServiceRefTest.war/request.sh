#!/bin/bash

curl -i -H "Content-Type: text/xml" -d "<env:Envelope xmlns:env='http://schemas.xmlsoap.org/soap/envelope/'><env:Header/><env:Body><ns2:invokeHelloWorld xmlns:ns2='http://jaxws.gss.redhat.com/'><arg0>Kyle</arg0></ns2:invokeHelloWorld></env:Body></env:Envelope>" http://localhost:8080/webServiceRefTest/clientEndpoint
