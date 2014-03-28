#!/bin/bash

java \
  -Djava.awt.headless=true \
  -Dorg.apache.cxf.logging.enabled=true \
  -Djava.util.logging.manager=org.jboss.logmanager.LogManager \
  -Dlogging.configuration=file:./logging.properties \
  -jar $JBOSS_HOME/jboss-modules.jar \
  -mp $JBOSS_HOME/modules \
  -cp .:WEB-INF/classes \
  -dep org.jboss.ws.cxf.jbossws-cxf-client,org.jboss.logging,org.apache.cxf,org.apache.cxf.impl,org.jboss.logmanager \
  com.redhat.gss.wsrm.TestClient
# -agentlib:jdwp=transport=dt_socket,address=8787,server=y,suspend=y \
