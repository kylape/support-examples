/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import org.apache.log4j.Logger;

@javax.jws.WebService(serviceName="HelloWSBad", portName="hello")
public class HelloWSBad
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public String hello(String input) throws Exception
  {
    log.error(input);
    return "Hello, " + input;
  }
}
