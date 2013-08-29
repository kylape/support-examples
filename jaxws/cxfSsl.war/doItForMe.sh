#!/bin/bash

#This script assumes 
# - you want to use standalone mode
# - you have a clean installation of EAP 6
# - a JDK is installed on your $PATH
# - ant is on your $PATH
# - you have no other EAP 6/AS 7 processes running
# - JBOSS_HOME and JAVA_HOME are properly set

ant setup-example-two-way-ssl

if [ "x$JBOSS_HOME" = "x" ]; then
  echo "Must set JBOSS_HOME"
  exit 1
fi

ant #Build the project

echo "Copying jbossweb.keystore"
cp jbossweb.keystore $JBOSS_HOME/standalone/configuration

if [ $? -gt 0 ]; then
  exit $?
fi

echo "Starting JBoss..."
$JBOSS_HOME/bin/standalone.sh -Djavax.net.debug=all > console.log &
sleep 10
echo "Adding HTTPS connector..."
$JBOSS_HOME/bin/jboss-cli.sh -c --file=installHttps.cli
$JBOSS_HOME/bin/jboss-cli.sh -c --commands="deploy dist/cxfSsl.war"
ant test

echo "Removing HTTPS connector..."
$JBOSS_HOME/bin/jboss-cli.sh -c --file=uninstallHttps.cli
$JBOSS_HOME/bin/jboss-cli.sh -c --commands="undeploy cxfSsl.war"
echo "Stopping JBoss..."
kill `jps | grep "jboss-modules.jar" | cut -f 1 -d " "`

#Clean things up so this script can be re-run more easily
rm jbossweb.keystore* client.keystore*
