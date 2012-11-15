/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

@javax.jws.WebService(endpointInterface="com.redhat.gss.ws.WsIntfc")
@javax.ejb.Stateless
@org.apache.cxf.feature.Features(features={"org.apache.cxf.feature.LoggingFeature"})
public class TestWs implements WsIntfc
{
  public void test() throws MyException
  {
    MyException e = new MyException("Brief message");
    e.setFrom("Kyle");
    e.setId(2345);
    e.setSummary("The error is bad");
    throw e;
  }
}
