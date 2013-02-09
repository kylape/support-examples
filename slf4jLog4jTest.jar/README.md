##SLF4J via Log4J in EAP 6

This deployment has a simple EJB with one `@PostConstruct`-annotated method.
This EJB also has two loggers, one from SLF4J and the other from Log4J.  The
deployment contains a `log4j.xml` that defines a file appender to write all
application logs to a file called `application.log`.  The
`@PostConstruct`-annotated method will log something using both the SLF4J and
Log4J loggers, thus testing to see if both log messages end up in
application.log as desired.

This example will show how JBoss can configure complex logging requirments (e.g.
SLF4J and Log4J in the same deployment) without the need for any special
configuration or inclusion of additional libraries.
