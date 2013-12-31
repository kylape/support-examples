#!/bin/bash

ant setup-example-two-way-ssl

if [ "x$JBOSS_HOME" = "x" ]; then
  echo "Must set JBOSS_HOME"
  exit 1
fi

ant deploy #Build the project

if [ $? -gt 0 ]; then
  exit $?
fi

echo "Copying jbossweb.keystore"
cp WEB-INF/classes/jbossweb.keystore $JBOSS_HOME/server/default/conf

if [ $? -gt 0 ]; then
  exit $?
fi

cat httpsConnector.xml

read -p "Please make sure you've installed the above HTTPS connector in $JBOSS_HOME/server/default/deploy/jbossweb.xml/server.xml. Press enter to continue."
read -p "Please start JBoss now. Press enter when the startup has completed."

curl -s -H "Content-Type: text/xml" -d "`cat request.xml`" http://localhost:8080/cxfSsl/clientEndpoint | xmllint --format -
echo

echo "Please stop JBoss before running the test again. "
sleep 1

#Clean things up so this script can be re-run more easily
rm WEB-INF/classes/jbossweb.keystore* WEB-INF/classes/client.keystore*
