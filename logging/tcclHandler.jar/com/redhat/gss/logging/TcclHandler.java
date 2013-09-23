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
  private final Map<String, FileHandler> fileHandlers = new HashMap<String, FileHandler>();
  private Map<String, String> deploymentMap = null;
  private String dirName = null;
  private boolean logSystemMessages = false;
  
  private ThreadLocal<Boolean> invoked = new ThreadLocal<Boolean>() {
    protected Boolean initialValue()
    {
      return Boolean.FALSE;
    }
  };
  
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
      //"myDeployment.war:funName"
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
    if(invoked.get().equals(Boolean.TRUE))
      return;
    else
        invoked.set(Boolean.TRUE);

    try
    {
      String deployment = getDeploymentName();

      if(deployment == null)
        return;

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

      // TODO why is this here?
      if(!fileHandler.isAutoFlush())
      {
        fileHandler.flush();
      }
    }
    finally
    {
      invoked.set(Boolean.FALSE);
    }
  }

  private FileHandler createFileHandler(String logName)
  {
    try
    {
      // TODO add ability to rotate
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

  private String getDeploymentName()
  {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();

    if(cl instanceof ModuleClassLoader)
    {
      ModuleClassLoader mcl = (ModuleClassLoader)cl;
      String moduleName = mcl.getModule().getIdentifier().toString();
      if(moduleName.startsWith("deployment"))
      {
        // Strip the deployment prefix and the slot suffix
        return moduleName.substring(moduleName.indexOf(".") + 1, moduleName.indexOf(":"));
      }
    }

    // Either not ModuleClassLoader or not a message from an application thread
    if(logSystemMessages)
      return SERVER;
    else
      return null;
  }
  
  public String getDirName()
  {
    return this.dirName;
  }
  
  public void setDirName(String dirName)
  {
    this.dirName = dirName;
  }

  public boolean getLogSystemMessages()
  {
    return this.logSystemMessages;
  }
  
  public void setLogSystemMessages(boolean logSystemMessages)
  {
    this.logSystemMessages = logSystemMessages;
  }
}
