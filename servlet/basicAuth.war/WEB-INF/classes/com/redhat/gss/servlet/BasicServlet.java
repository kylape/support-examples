package com.redhat.gss.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.Principal;

import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet
{
  private static final String SUBJECT_CONTEXT_KEY = "javax.security.auth.Subject.container";

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  {
    OutputStreamWriter osw = null;
    try
    {
      osw = new OutputStreamWriter(response.getOutputStream());
      Subject subject = (Subject) PolicyContext.getContext(SUBJECT_CONTEXT_KEY);
      StringBuilder builder = new StringBuilder();
      for(Principal p : subject.getPrincipals())
      {
        builder.append(p.getName());
        builder.append(", ");
      }
      builder.deleteCharAt(builder.length()-1);
      builder.deleteCharAt(builder.length()-1);
      osw.write("User(s): " + builder.toString() + "\n");
    }
    catch(Exception ioe)
    {
      response.setStatus(500);
    }
    finally
    {
      try
      {
        osw.close();
      }
      catch(Exception e)
      {
        //nothing
      }
    }
  }
}
