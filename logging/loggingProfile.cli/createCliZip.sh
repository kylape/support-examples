#!/bin/bash

cd loggingProfile.war
ant

if [ $? != "0" ]; then
  exit 1
fi

cd dist
cp ../../*.scr .
zip loggingProfile.cli *
mv loggingProfile.cli ../..
rm *.scr
cd ../..
mkdir dist
mv loggingProfile.cli dist
