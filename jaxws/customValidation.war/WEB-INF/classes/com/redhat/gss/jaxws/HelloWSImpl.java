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
import java.util.Date;
import org.apache.cxf.annotations.EndpointProperty;
import org.apache.cxf.annotations.EndpointProperties;

// @org.apache.cxf.annotations.Logging(pretty=true)
// @org.apache.cxf.feature.Features(features={"org.apache.cxf.feature.LoggingFeature"})
// @org.jboss.ws.annotation.SchemaValidation
@javax.jws.WebService(serviceName="HelloWS", portName="hello")
// @EndpointProperties (
  // @EndpointProperty(key="set-jaxb-validation-event-handler", value={"false"})
  // @EndpointProperty(key="jaxb-validation-event-handler", value="com.redhat.gss.jaxws.MyValidationEventHandler")
// )
public class HelloWSImpl
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public ReturnDate hello(String name)
  {
    log.info("Hello, " + name);
    ReturnDate d = new ReturnDate();
    if(name.startsWith("K"))
    {
      return d;
    }
    else
    {
      d.setDate(new Date());
      return d;
    }
  }
}
