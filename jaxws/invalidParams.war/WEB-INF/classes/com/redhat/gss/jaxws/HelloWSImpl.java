/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import org.apache.log4j.Logger;
import java.util.Date;

@javax.jws.WebService(serviceName="HelloWS", portName="hello", endpointInterface="com.redhat.gss.jaxws.HelloWS")
public class HelloWSImpl
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public String hello(MyObject input)
  {
    log.info("What a date should look like: " + new Date());
    log.info("Date:  " + input.getDate());
    log.info("Count: " + input.getCount());
    return "Hello";
  }
}
