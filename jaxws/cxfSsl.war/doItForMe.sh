#!/bin/bash

#This script assumes 
# - you want to use standalone mode
# - you have a clean installation of EAP 6
# - a JDK is installed on your $PATH
# - ant is on your $PATH
# - you have no other EAP 6/AS 7 processes running

ant setup-example-two-way-ssl
./installSslConnector.sh
if [ $? -gt 0 ]; then
  exit $?
fi
echo "Starting JBoss..."
$JBOSS_HOME/bin/standalone.sh > console.log &
sleep 10
ant deploy
sleep 7
ant test
echo "Stopping JBoss..."
kill `jps | grep "jboss-modules.jar" | cut -f 1 -d " "`
