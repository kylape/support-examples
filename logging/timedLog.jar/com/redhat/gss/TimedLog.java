package com.redhat.gss;

import org.jboss.logging.Logger;
import javax.annotation.PostConstruct;

import java.util.concurrent.atomic.AtomicBoolean;

@javax.ejb.Singleton
@javax.ejb.Startup
public class TimedLog {
  private static Logger log = Logger.getLogger(TimedLog.class);

  @PostConstruct
  public void doLog() throws Exception {
    final AtomicBoolean go = new AtomicBoolean(true);
    Thread shutdownThread = new Thread(new Runnable() {
      public void run() {
        go.set(false);
      }
    });
    Runtime.getRuntime().addShutdownHook(shutdownThread);
    while(go.get()) {
      log.info("Logging once a second to check the timestamp.");
      Thread.sleep(1000);
    }
  }
}
