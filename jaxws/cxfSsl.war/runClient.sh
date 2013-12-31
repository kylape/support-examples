#!/bin/bash

#This script assumes 
# - you want to use standalone mode
# - you have a clean installation of EAP 6
# - a JDK is installed on your $PATH
# - ant is on your $PATH
# - you have no other EAP 6/AS 7 processes running
# - JBOSS_HOME and JAVA_HOME are properly set

if [ "x$JBOSS_HOME" = "x" ]; then
  echo "Must set JBOSS_HOME"
  exit 1
fi

if [ "x$JAVA_HOME" = "x" ]; then
  echo "Must set JAVA_HOME"
  exit 1
fi

ant setup-example-two-way-ssl

ant #Build the project

echo "Copying jbossweb.keystore"
cp WEB-INF/classes/jbossweb.keystore $JBOSS_HOME/standalone/configuration

if [ $? -gt 0 ]; then
  exit $?
fi

echo "Starting JBoss..."
$JBOSS_HOME/bin/standalone.sh -Djavax.net.debug=all > server.log &
sleep 5
echo "Adding HTTPS connector..."
$JBOSS_HOME/bin/jboss-cli.sh -c --file=installHttps.cli

$JBOSS_HOME/bin/jboss-cli.sh -c --commands="deploy dist/cxfSsl.war --force"
if [ "$1" == "-s" ]; then
  curl -s -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/cxfSsl/clientEndpoint | xmllint --format -
  echo
else
  ant test
fi

$JBOSS_HOME/bin/jboss-cli.sh -c --commands="undeploy cxfSsl.war"

echo "Removing HTTPS connector..."
$JBOSS_HOME/bin/jboss-cli.sh -c --file=uninstallHttps.cli
echo "Stopping JBoss..."
kill `jps | grep "jboss-modules.jar" | cut -f 1 -d " "`

#Clean things up so this script can be re-run more easily
rm WEB-INF/classes/jbossweb.keystore* WEB-INF/classes/client.keystore*
