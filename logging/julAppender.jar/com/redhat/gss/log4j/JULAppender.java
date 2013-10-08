package com.redhat.gss.log4j;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.LogManager;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.AppenderSkeleton;

public class JULAppender extends AppenderSkeleton {

  private static final HashMap<String, Level> levelMap = new HashMap<String, Level>();
  private static boolean hasTimeStampMethod = false;

  static {
    levelMap.put("OFF", Level.OFF);
    levelMap.put("ERROR", Level.SEVERE);
    levelMap.put("WARN", Level.WARNING);
    levelMap.put("INFO", Level.INFO);
    levelMap.put("DEBUG", Level.FINE);
    levelMap.put("TRACE", Level.FINEST);
    levelMap.put("ALL", Level.ALL);

    for(Method m : LoggingEvent.class.getMethods()) {
      if(m.getName().equals("getTimeStamp")) {
        hasTimeStampMethod = true;
        break;
      }
    }
  }

  public JULAppender() {
  }

  public void activateOptions() {
  }

  public void append(LoggingEvent event) {
    Level level = convertLevel(event.getLevel().toString());
    String loggerName = event.getLoggerName();
    String message = event.getMessage().toString();
    long millis = hasTimeStampMethod ? event.getTimeStamp() : System.currentTimeMillis();
    LogRecord record = new LogRecord(level, message);
    record.setMillis(millis);
    record.setLoggerName(loggerName);
    LogManager.getLogManager().getLogger(loggerName).log(record);
  }

  private Level convertLevel(String logbackLevel) {
    Level ret = levelMap.get(logbackLevel);

    if(ret == null)
      return Level.INFO;
    else
      return ret;
  }

  public boolean requiresLayout() {
    return false;
  }

  public void close() {
  }
}
