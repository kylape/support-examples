###Simple Logging Handler

This project installs a simple java.util.logging.Handler into JBoss.  The handler esentially duplicates all log messages to `STDOUT`.  All custom handlers need to call `isLoggable()` to check the logging level and also need to use its formatter to format each message.  Examine `standalone-changes.diff` if you want to see the changes necessary to install the custom logger in JBoss.

To build and deploy this example, run `./installHandler.sh`, which will run ant, create the module, and make the changes to `standalone.xml`.  The diff assumes the `standalone.xml` has been unmodified from the stock version of EAP 6.
