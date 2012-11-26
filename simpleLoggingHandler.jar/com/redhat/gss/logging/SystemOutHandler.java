package com.redhat.gss.logging;

import java.util.logging.Handler;
import java.util.logging.ErrorManager;
import java.util.logging.LogRecord;
import java.util.logging.Formatter;

public class SystemOutHandler extends Handler 
{
  @Override
  public void publish(final LogRecord logRecord) 
  {
    if(logRecord != null && isLoggable(logRecord)) 
    {
      final String formatted;
      final Formatter formatter = getFormatter();
      try 
      {
          formatted = formatter.format(logRecord);
      } 
      catch (Exception ex) 
      {
          reportError("Formatting error", ex, ErrorManager.FORMAT_FAILURE);
          return;
      }
      if (formatted.length() == 0) 
      {
          // nothing to write; don't bother
          return;
      }
      System.out.print(formatted);
    }
  }

  @Override
  public void flush() 
  {
  }

  @Override
  public void close() throws SecurityException 
  {
  }
}
