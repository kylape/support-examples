#!/bin/bash

curl -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/log4jIsolated/LoggingTest
echo
echo -------------------------------------------------------------------------------- 
echo LOG OUTPUT
echo -------------------------------------------------------------------------------- 
tail $JBOSS_HOME/log4j-isolated.log
