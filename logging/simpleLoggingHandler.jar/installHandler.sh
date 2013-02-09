#!/bin/bash

if [ x$JBOSS_HOME = "x" ]; then
  echo "Please set JBOSS_HOME"
  exit 1
fi

echo "Running ant..."
ant

if [ $? != 0 ]; then
  echo "ant failed"
  exit 1
fi

module_dir=$JBOSS_HOME/modules/com/redhat/gss/logging/main

mkdir -p $module_dir
cp module.xml $module_dir
cp dist/simpleLoggingHandler.jar $module_dir
patch -d $JBOSS_HOME -p1 < standalone-changes.diff
