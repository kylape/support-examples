/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.modules;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;

import org.jboss.modules.Module;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.modules.ModuleClassLoader;

//@javax.jws.WebService(endpointInterface="com.redhat.gss.modules.ModuleTestIntfc")
@javax.servlet.annotation.WebServlet(urlPatterns={"/*"})
public class ModuleTest extends HttpServlet implements ModuleTestIntfc
{
  public String getDeploymentModule()
  {
    ModuleClassLoader cl = (ModuleClassLoader)Thread.currentThread().getContextClassLoader();
    Module module = cl.getModule();
    return module.toString();
  }

  public String testSaajMessageFactory() throws SOAPException
  {
    MessageFactory mf = MessageFactory.newInstance();
    return getModuleOfClass(mf.getClass());
  }

  public String getModuleOfClass(String className)
  {
    Class clazz = null;
    try
    {
      clazz = Class.forName(className);
    }
    catch(ClassNotFoundException cnfe)
    {
      return "Class not found";
    }
    catch(NoClassDefFoundError ncdfe)
    {
      return "No class def found";
    }

    if(clazz == null)
      return "Class not found";
    else
      return getModuleOfClass(clazz);
  }

  private String getModuleOfClass(Class clazz)
  {
    ModuleClassLoader cl = null;

    try
    {
      cl = ((ModuleClassLoader)clazz.getClassLoader());
    }
    catch(ClassCastException cce)
    {
      return "Class found, but the classloader was not a ModuleClassLoader. " +
             "The class was likely loaded by a system classloader before JBoss " + 
             "Modules was fully bootstrapped.";
    }

    if(cl == null)
      return "Class found, but it's probably a JDK class";

    Module m = cl.getModule();
    ModuleIdentifier mi = m.getIdentifier();
    return mi.toString();
  }

  public String getResource(String resourceName)
  {
    URL url = null;

    try
    {
      url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
    }
    catch(Exception e)
    {
      return "Resource not found: " + e.getMessage();
    }

    if(url == null)
    {
      return "Resource not found";
    }
    else
    {
      return url.toExternalForm();
    }
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    String responseString = "";
    String command = request.getParameter("command");
    if(command == null || command.equals(""))
    {
      responseString = "Command not found";
    }
    else if(command.equals("getDeploymentModule"))
    {
      responseString = getDeploymentModule();
    }
    else if(command.equals("testSaajMessageFactory"))
    {
      try
      {
        responseString = testSaajMessageFactory();
      }
      catch(SOAPException e)
      {
        response.setStatus(500);
        response.getWriter().println("Error creating SAAJ factory");
        response.getWriter().close();
        return;
      }
    }
    else if(command.equals("getModuleOfClass"))
    {
      String className = request.getParameter("className");
      if(className == null || className.equals(""))
      {
        responseString = "Class name not found";
      }
      else
      {
        responseString = getModuleOfClass(className);
      }
    }
    else if(command.equals("getResource"))
    {
      String resourceName = request.getParameter("resourceName");
      if(resourceName == null || resourceName.equals(""))
      {
        responseString = "Resource name must be defined";
      }
      else
      {
        responseString = getResource(resourceName);
      }
    }

    response.setContentType("text/plain");

    PrintWriter writer = null;
    try
    {
      writer = response.getWriter();
      writer.println(responseString);
    }
    finally
    {
      writer.close();
    }
  }
}
