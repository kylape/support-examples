/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.logging;

import java.util.logging.Logger;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

//@javax.servlet.annotation.WebServlet(urlPatterns={"/*"})
public class LoggingServlet extends HttpServlet
{
  private Logger log  = Logger.getLogger(this.getClass().getName());
  private Logger log2 = Logger.getLogger("some.fun.class.Name");

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    log.info("This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement. This is a really big log statement.");

    log2.fine("This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement. This is a really fine log statement.");
  }
}
