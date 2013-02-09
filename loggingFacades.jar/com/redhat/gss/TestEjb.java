/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss;

@javax.ejb.Singleton
@javax.ejb.Startup
@javax.jws.WebService
public class TestEjb
{
  private org.slf4j.Logger slf4j = org.slf4j.LoggerFactory.getLogger(this.getClass());
  private org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(this.getClass());
  private org.jboss.logging.Logger jbossLogging = org.jboss.logging.Logger.getLogger(this.getClass());
  private java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(this.getClass().getName());

  @javax.annotation.PostConstruct
  public void test()
  {
    slf4j.info("sfl4j");
    log4j.info("log4j");
    jbossLogging.info("jbossLogging");
    julLogger.info("julLogger");
  }
}
