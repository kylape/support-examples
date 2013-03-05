##Commons Logging Example

This is an example based off of a test case created for
[JBPAPP-6524](https://issues.jboss.org/browse/JBPAPP-6524).  It initializes
Commons Logging and directs it to use Log4J.  `log4j.properties` configures the
root logger to write all log messages to a file called `custLog.log`.

It seems that in JBoss EAP if you set the system property
`org.apache.commons.logging.use_tccl=false`, then log messages will be printed
to the console (i.e. system Log4J context) rather than `custLog.log` (i.e.
deployment Log4J context).
