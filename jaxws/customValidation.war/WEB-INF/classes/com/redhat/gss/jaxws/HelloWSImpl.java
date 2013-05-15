/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import javax.xml.ws.spi.Provider;
import org.jboss.logging.Logger;
import javax.naming.InitialContext;

@javax.jws.WebService(serviceName="HelloWS", portName="hello")
//@org.jboss.wsf.spi.annotation.WebContext(transportGuarantee="CONFIDENTIAL")
//@org.apache.cxf.annotations.Logging(pretty=true)
//@org.apache.cxf.feature.Features(features={"org.apache.cxf.feature.LoggingFeature"})
public class HelloWSImpl implements java.io.Serializable
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public String hello(String name)
  {
    try
    {
      // new InitialContext().bind(this.getClass().getName(), this);
      log.info("Hello, " + name);
      return "Hello, " + name;
    }
    catch(Exception e)
    {
      log.error("", e);
      return "Fail";
    }
  }
}
