#!/bin/bash

if [ "x$JBOSS_HOME" = "x" ]; then
  echo "Must set JBOSS_HOME"
  exit 1
fi

echo "Copying jbossweb.keystore"
cp jbossweb.keystore $JBOSS_HOME/standalone/configuration
patch -p1 -d $JBOSS_HOME < sslConnector.diff
