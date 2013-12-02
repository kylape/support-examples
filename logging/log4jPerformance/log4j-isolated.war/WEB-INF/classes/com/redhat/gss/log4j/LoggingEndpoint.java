/*
 * Copyright 2013 Red Hat, Inc.
 *
 * This file incorporates work covered by the following notice(s):
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.gss.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

import javax.jws.WebParam;
import javax.jws.WebResult;

@javax.jws.WebService
public class LoggingEndpoint
{
  public @WebResult(name="time") double createLoggerTest(@WebParam(name="count") int count)
  {
    long start = System.nanoTime();
    String loggerName = "a";
    for(int i=0; i<count; i++)
    {
      Logger logger = Logger.getLogger(loggerName);
      loggerName = incrementName(loggerName);
    }
    long end = System.nanoTime();
    return new Double((end - start) / 1000000.0);  //in milliseconds
  }

  /*
   * Calculate the time it takes to log a number of messages with a given logger.
   * Obtaining the logger will not be included in the calculation
   */
  public @WebResult(name="time") double logMessages(
    @WebParam(name="message") String message, 
    @WebParam(name="loggerName") String loggerName, 
    @WebParam(name="count") int count, 
    @WebParam(name="levelName") String levelName)
  {
    long start = System.nanoTime();
    Logger logger = Logger.getLogger(loggerName);
    Level level = Level.toLevel(levelName);
    for(int i=0; i<count; i++)
    {
      logger.log(level, message);
    }
    long end = System.nanoTime();
    return new Double((end - start) / 1000000.0);  //in milliseconds
  }

  private static String incrementName(String name)
  {
    char[] cc = name.toCharArray();
    for(int i=cc.length-1; i>=0; i--)
    {
      char c = cc[i];
      if(c == 'z')
      {
        cc[i] = 'a';
        if(i == 0)
        {
          char[] newcc = new char[cc.length+1];
          System.arraycopy(cc, 0, newcc, 1, cc.length);
          newcc[0] = 'a';
          cc = newcc;
        }
      }
      else
      {
        cc[i] = ++c;
        break;
      }
    }
    return new String(cc);
  }
}
