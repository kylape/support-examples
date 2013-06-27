/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface="com.redhat.gss.ws.HelloWSInterface")
public class HelloWS implements HelloWSInterface
{
  public String hello(@WebParam(name="cat") Cat cat)
  {
    if("Tiger".equals(cat.getType()))
    {
      return "I'm scared of tigers!";
    }
    else
    {
      return "Hello, " + cat.getName() + "!";
    }
  }
}
