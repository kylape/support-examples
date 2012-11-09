/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.saaj;

import javax.xml.soap.MessageFactory;
import org.jboss.modules.ModuleClassLoader;
import org.jboss.modules.Module;

@javax.jws.WebService
public class SaajTest
{
  public String test() throws Exception
  {
    MessageFactory mf = MessageFactory.newInstance();
    ModuleClassLoader cl = (ModuleClassLoader)Thread.currentThread().getContextClassLoader();
    Module module = cl.getModule();
    return module.toString();
    /*
    return ((ModuleClassLoader)mf.getClass().getClassLoader()).getModule().getIdentifier().toString();
    */
  }
}
