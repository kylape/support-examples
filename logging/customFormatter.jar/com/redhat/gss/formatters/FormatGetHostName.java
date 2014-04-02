package com.redhat.gss.formatters;

import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: tsandor
 * Date: 3/24/14
 * Time: 3:47 PM
 * Singleton class to get the "hostname" (%h) for logging.
 *
 */
public class FormatGetHostName {

    //  String hostname = null;
    private AtomicReference hostname;
    // Private constructor prevents instantiation from other classes
    private FormatGetHostName() {
        hostname = new AtomicReference();
        // Set IP to something, then invoke a thread to get the hostname itself, if the
        // thread takes time to complete (DNS is slow), the IP address will be used until
        // the thread completes (then the "hostname" should be used).
        hostname.set(getIp());
        Thread lThread = (new Thread("loggerFormatGetHostName") {
            public void run() {
                try {
                    hostname.set(InetAddress.getLocalHost().getHostName());
                    System.out.println("FormatGetHostName() obtained hostname via getHostName()=" + hostname.get().toString());
                } catch (Throwable e) {
                    // do not log this, its confusing to customers.  Its not an issue
                    // the IP will be used instead (as getIp() was used).
                }
            }
        });
        lThread.setDaemon(true);
        lThread.start();
    }

    /**
     * HostNameHolder is loaded on the first execution of FormatGetHostName.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class HostNameHolder {
        private static final FormatGetHostName INSTANCE = new FormatGetHostName();
    }

    public static FormatGetHostName getInstance() {
        return HostNameHolder.INSTANCE;
    }

    public String getHostname() {
        return (FormatGetHostName.getInstance().hostname.get().toString());
    }

    public String getIp() {
        String loggerIp = System.getProperty("jboss.bind.address");
        if (loggerIp != null) {
            System.out.println("FormatGetHostName.getIp() obtained IP address via property jboss.bind.address=" +
                    loggerIp);
        }
        if (loggerIp == null || loggerIp.length() == 0 || "0.0.0.0".equals(loggerIp)) {
            try {
                loggerIp = InetAddress.getLocalHost().getHostAddress();
                System.out.println("FormatGetHostName.getIp() obtained IP address via getHostAddress()=" +
                        loggerIp);
            } catch (Exception e) {

            }
        }
        return loggerIp;
    }
}
