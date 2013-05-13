package com.redhat.gss.logging;

import org.jboss.modules.ModuleClassLoader;
import org.jboss.logmanager.ExtHandler;
import org.jboss.logmanager.ExtLogRecord;
import org.jboss.logmanager.handlers.FileHandler;
import java.util.logging.Handler;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/*
 * Logs message from each application to a different log file.
 * If logs aren't from an application, it logs to server.log
 */
public class TcclHandler extends ExtHandler
{
  private static final String SERVER = "server";
  private static final Map<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();
  private static Map<String, String> deploymentMap = null;
  private ThreadLocal<Boolean> invoked = new ThreadLocal<Boolean>() {
    protected Boolean initialValue()
    {
      return Boolean.FALSE;
    }
  };
  private Object lock = new Object();

  private String dirName = null;
  
  public String getLogNames()
  {
    StringBuilder builder = new StringBuilder();
    for(String key : deploymentMap.keySet())
    {
      String logName = deploymentMap.get(key);
      builder.append(key).append(":").append(logName).append(",");
    }
    builder.deleteCharAt(builder.length()-1);
    return builder.toString();
  }
  
  public void setLogNames(String logNames)
  {
    this.deploymentMap = new HashMap<String, String>();
    for(String stringPair : logNames.split(","))
    {
      //Example format
      //"myDeployment:funName"
      //That takes myDeployment.war and writes it to funName.log
      String[] pair = stringPair.split(":");
      if(pair.length != 2)
        throw new IllegalStateException("Invalid format of log name string");
      deploymentMap.put(pair[0], pair[1]);
    }
  }

  @Override
  protected void doPublish(final ExtLogRecord record)
  {
    synchronized(lock) //probly not necessary
    {
      if(invoked.get().equals(Boolean.TRUE))
      {
        return;
      }
      else
      {
          invoked.set(Boolean.TRUE);
      }
    }

    String deployment = getDeploymentName();

    // Did we configure a custom log name for this deployment?
    String logName = deploymentMap.get(deployment);
    if(logName == null)
    {
      logName = deployment; // default log name
    }
    
    FileHandler fileHandler = fileHandlers.get(logName);
    if(fileHandler == null)
    {
      fileHandler = createFileHandler(logName);
    }
    fileHandler.publish(record);

    if(!fileHandler.isAutoFlush())
    {
      fileHandler.flush();
    }

    synchronized(lock)
    {
      invoked.set(Boolean.FALSE);
    }
  }

  private FileHandler createFileHandler(String logName)
  {
    try
    {
      File f = new File(dirName + File.separator + logName + ".log");
      FileHandler newHandler = new FileHandler(getFormatter(), f);
      fileHandlers.put(logName, newHandler);
      return newHandler;
    }
    catch(FileNotFoundException e)
    {
      throw new IllegalStateException("Cannot create log file", e);
    }
  }

  private static String getDeploymentName()
  {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();

    if(cl instanceof ModuleClassLoader)
    {
      ModuleClassLoader mcl = (ModuleClassLoader)cl;
      String moduleName = mcl.getModule().getIdentifier().toString();
      if(moduleName.startsWith("deployment"))
      {
        List<String> segments = new ArrayList<String>();
        for(String segment : moduleName.split("\\."))
        {
          segments.add(segment);
        }
        // Remove 'deployment' from the beginning and the suffix from the end
        segments.remove(0);
        segments.remove(segments.size()-1);
        StringBuilder builder = new StringBuilder();
        for(String segment : segments)
        {
          builder.append(segment + ".");
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
      }
      else
      {
        return SERVER;
      }
    }
    else // Not instanceof ModuleClassLoader
    {
      return SERVER;
    }
  }
  
  public String getDirName()
  {
    return this.dirName;
  }
  
  public void setDirName(String dirName)
  {
    this.dirName = dirName;
  }
}
