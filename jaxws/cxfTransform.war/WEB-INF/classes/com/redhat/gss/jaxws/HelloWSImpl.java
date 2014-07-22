/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import org.jboss.logging.Logger;
import org.apache.cxf.interceptor.OutInterceptors;

@javax.jws.WebService(serviceName="HelloWS", portName="hello", wsdlLocation="WEB-INF/classes//wsdl/dir with spaces/wsdl/HelloWS.wsdl")
@OutInterceptors(classes={
  com.redhat.gss.jaxws.ResponseTransformerInterceptor.class
})
public class HelloWSImpl
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public String hello(String name)
  {
    try
    {
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
