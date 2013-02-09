##Logging Facade Example

This deployment has a simple EJB with one `@PostConstruct`-annotated method.
I've included four different types of loggers:

- `java.util.logging.Logger`
- `org.slf4j.Logger`
- `org.apache.log4j.Logger`
- `org.jboss.logging.Loggger`

The `@PostConstruct`-annotated method will log something using all the loggers,
demonstrating how JBoss can configure each logging facade via the logging
subsystem.

This example will show how JBoss can configure complex logging requirments (e.g.
SLF4J and Log4J in the same deployment) without the need for any special
configuration or inclusion of additional libraries.
