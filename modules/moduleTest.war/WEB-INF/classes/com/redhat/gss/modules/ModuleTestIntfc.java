/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.modules;

import javax.xml.soap.SOAPException;

import javax.jws.WebMethod;
import javax.jws.WebParam;

@javax.jws.WebService
public interface ModuleTestIntfc
{
  @WebMethod
  public String getDeploymentModule();

  @WebMethod
  public String testSaajMessageFactory() throws SOAPException;

  @WebMethod
  public String getModuleOfClass(@WebParam(name="className") String className); 
}
