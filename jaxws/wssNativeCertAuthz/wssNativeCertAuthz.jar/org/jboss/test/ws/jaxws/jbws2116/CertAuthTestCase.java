/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.test.ws.jaxws.jbws2116;

import java.net.URL;
import java.io.File;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.jboss.ws.core.StubExt;
import org.jboss.wsf.test.JBossWSTest;
import org.jboss.wsf.test.JBossWSTestSetup;

/**
 * Test case for certificate authentication & authorization with WS-Security
 * http://jira.jboss.org/jira/browse/JBWS-2116
 *
 * @author alessio.soldano@jboss.com
 * @since 24-May-2008
 */
public class CertAuthTestCase
{
   private String TARGET_ENDPOINT_ADDRESS = "http://localhost:8080/wssNativeCertAuthz";
   
   public static void main(String[] args) throws Exception
   {
      CertAuthTestCase test = new CertAuthTestCase();
      test.testAuthAlice();
      test.testAuthJohn();
   }

   public void testAuthAlice() throws Exception
   {
      setEnvironment("alice");
      Hello port = getPort();
      String msg = "Hi!";
      try
      {
         String result = port.echo(msg);
         if(!msg.equals(result))
         {
            throw new Exception("msg doesn't equal result");
         }
         result = port.echo2(msg);
         if(!msg.equals(result))
         {
            throw new Exception("msg doesn't equal result");
         }
      }
      catch (Exception e)
      {
         System.out.println("fail");
         e.printStackTrace();
      }
   }
   
   public void testAuthJohn() throws Exception
   {
      setEnvironment("john");
      Hello port = getPort();
      String msg = "Hi!";
      try
      {
         String result = port.echo(msg);
         if(!msg.equals(result))
         {
            throw new Exception("msg doesn't equal result");
         }
      }
      catch (Exception e)
      {
         System.out.println("fail");
         e.printStackTrace();
      }
      try
      {
         port.echo2(msg);
         throw new Exception("John shouldn't be allowed to run this method!");
      }
      catch (Exception e)
      {
         //OK
      }
   }
   
   private void setEnvironment(String name)
   {
      //Setup values
      System.setProperty("org.jboss.ws.wsse.keyStore", new File("META-INF/" + name + "-sign.jks").getPath());
      System.setProperty("org.jboss.ws.wsse.trustStore", new File("META-INF/wsse10.truststore").getPath());
      System.setProperty("org.jboss.ws.wsse.keyStorePassword", "password");
      System.setProperty("org.jboss.ws.wsse.trustStorePassword", "password");
      System.setProperty("org.jboss.ws.wsse.keyStoreType", "jks");
      System.setProperty("org.jboss.ws.wsse.trustStoreType", "jks");
   }

   private Hello getPort() throws Exception
   {
      URL wsdlURL = new URL(TARGET_ENDPOINT_ADDRESS + "?wsdl");
      QName serviceName = new QName("http://org.jboss.ws/wssNativeCertAuthz", "HelloService");
      Hello port = Service.create(wsdlURL, serviceName).getPort(Hello.class);
      URL securityURL = this.getClass().getResource("/META-INF/jboss-wsse-client.xml");
      ((StubExt)port).setSecurityConfig(securityURL.toExternalForm());
      ((StubExt)port).setConfigName("Standard WSSecurity Client", "META-INF/jaxws-client-config.xml");
      ((BindingProvider)port).getRequestContext().put(StubExt.PROPERTY_AUTH_TYPE, StubExt.PROPERTY_AUTH_TYPE_WSSE);
      return port;
   }
}
