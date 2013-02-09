#!/bin/bash

if [ x$JBOSS_HOME == "x" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

cd jbosswsCertSecurityDomain.sar
ant deploy

cd ../wssNativeCertAuthz.jar
ant deploy

echo
echo "*** Run 'ant test' to execute the client ***"
echo
