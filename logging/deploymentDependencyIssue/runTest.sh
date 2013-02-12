#!/bin/bash

if [ "x$JBOSS_HOME" == "x" ]; then
  echo "JBOSS_HOME must be set"
  exit 1
fi

if [ "x`which ant`" == "x" ]; then
  echo "ant must be on the PATH"
  exit 1
fi

echo "Cleaning logs..."
rm *.log
cd deploymentB.jar
ant deploy
cd ../deploymentA.war
ant deploy
cd ..
$JBOSS_HOME/bin/standalone.sh > console.log &
echo "Waiting for JBoss to start..."
sleep 10
echo "curl http://localhost:8080/deploymentA/test?name=Kyle"
curl "http://localhost:8080/deploymentA/test?name=Kyle"
echo
echo "CONSOLE"
echo "================================================"
tail -n 3 console.log
echo "================================================"
echo
echo "my-log4j.log"
echo "================================================"
tail my-log4j.log
echo "================================================"
echo
JBOSS_PID=`jps | grep jboss-modules | cut -f 1 -d " "`
echo "Killing JBoss..."
echo "kill $JBOSS_PID"
kill $JBOSS_PID
