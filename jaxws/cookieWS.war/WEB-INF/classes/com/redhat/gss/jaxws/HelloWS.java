/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import javax.jws.WebResult;

@javax.jws.WebService
public interface HelloWS
{
  public String hello(String name);

  @WebResult(name="cookie") 
  public String getCookie();

  public void testCookie();
}
