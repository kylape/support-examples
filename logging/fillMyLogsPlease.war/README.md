###Servlet used to fill your logs

This servlet simply logs to two different `java.util.logging.Logger`s: `com.redhat.gss.logging.LoggingServlet` and `some.fun.class.Name`.  The statements are really long so testing for log rotation won't take as many invocations of the servlet.  Alos, the `INFO`-level message to `LoggingServlet` is logged 1000 times per invocation.

To invoke the servlet, run `curl curl http://localhost:8080/fillMyLogsPlease/logging`.  To invoke indefinitely, run `for (( ; ; )); do curl http://localhost:8080/fillMyLogsPlease/logging; done`.
