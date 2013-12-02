#!/bin/bash

extractTime() {
  result=`echo $1 | xmllint --format - | grep time | cut -c 13- | cut -f 1 -d "<"`
}

createLoggerTest() {
  request="<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\
    <env:Header/>\
    <env:Body>\
      <ns2:createLoggerTest xmlns:ns2=\"http://log4j.gss.redhat.com/\">\
        <count>$1</count>
      </ns2:createLoggerTest>\
    </env:Body>\
  </env:Envelope>"
  result=`curl -s -H "Content-Type: text/xml" -d "$request" http://localhost:8080/$contextPath/LoggingEndpoint`
  extractTime "$result"
}

logMessages() {
  request="<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\
    <env:Header/>\
    <env:Body>\
      <ns2:logMessages xmlns:ns2=\"http://log4j.gss.redhat.com/\">\
        <message>$1</message>
        <loggerName>$2</loggerName>
        <count>$3</count>
        <levelName>$4</levelName>
      </ns2:logMessages>
    </env:Body>\
  </env:Envelope>"
  result=`curl -s -H "Content-Type: text/xml" -d "$request" http://localhost:8080/$contextPath/LoggingEndpoint`
  extractTime "$result"
}

runTest() {
  createLoggerTest 873
  result1=$result
  # logMessages "Hello, world!" "a" "10000" "WARN"
  # result2=$result
  result2="0.0"
  logMessages "Hello, world!" "b" "1187" "DEBUG"
  result3=$result

  result=`echo "$result1 + $result2 + $result3" | bc`
  echo "Create Logger Test:          $result1"
  echo "Log Message above threshold: $result2"
  echo "Log Message below thredhold: $result3"
  echo "Total:                       $result"
}

if [ -z "$JBOSS_HOME" ]; then
  echo "Set JBOSS_HOME"
  exit 1
fi

read -p "Make sure you have a clean installation of EAP 6 and press ENTER when ready "
echo

echo "************** Building and deploying projects to JBoss **************"
cd log4j-jboss.war
ant deploy
cd ../log4j-isolated.war
ant deploy
cd ..

echo
read -p "Start JBoss and press ENTER when ready"
echo
echo Testing JBoss\'s Log4J implementation...

contextPath=log4j-jboss
runTest
jbossResult=$result

echo
read -p "Stop JBoss, add the system property -Dorg.jboss.as.logging.per-deployment=false, start JBoss, press ENTER "
echo
echo Testing original/isolated Log4J implementation...

contextPath=log4j-isolated
runTest
isolatedResult=$result

echo
echo ------------------------------------------------------------
echo JBOSS - ISOLATED = `echo "$jbossResult - $isolatedResult" | bc`
echo ------------------------------------------------------------
echo "Positive value means JBoss is slower.  Negative means JBoss is faster."
