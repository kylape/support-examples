##Logging test with deployment dependencies

Scenario:

- You include a custom Log4J configuration called `my-log4j.xml`:

```
<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/" debug="true">
  <appender name="file" class="org.apache.log4j.DailyRollingFileAppender">
    <param name="File" value="my-log4j.log"/>
    <param name="Append" value="false"/>
    <layout class="org.apache.log4j.PatternLayout">
      <param name="ConversionPattern" value="---ZZZ--- %d{ISO8601} [%t] [%F] %p %c %x - %m%n"/>
    </layout>
  </appender>
  <logger name="com.redhat.gss.log.test" additivity="true">
    <level value="INFO"/>
  </logger>
  <root>
    <priority value="WARN"/>
    <appender-ref ref="file"/>
  </root>
</log4j:configuration>
```

- You exclude all logging modules that are implicitly added by JBoss.
- You also add a dependency onto a deployment that contains a class you need:

```
<jboss-deployment-structure>
  <deployment>
    <exclusions>
      <module name="org.apache.log4j"/>
      <module name="org.slf4j"/>
      <module name="org.slf4j.ext"/>
      <module name="org.slf4j.impl"/>
      <module name="org.slf4j.jcl-over-slf4j"/>
      <module name="org.apache.commons.logging"/>
      <module name="org.jboss.logging"/>
      <module name="org.jboss.logging.jul-to-slf4j-stub"/>
      <module name="org.jboss.logmanager"/>
      <module name="org.jboss.log4j.logmanager"/>
    </exclusions>
    <dependencies>
      <module name="deployment.deploymentB.jar"/>
    </dependencies>
  </deployment>
</jboss-deployment-structure>
```

- You initialize Log4J in your application via a servlet listener:

```
public void contextInitialized(ServletContextEvent event)
{
  URL log4jXml = Thread.currentThread().getContextClassLoader().getResource("/my-log4j.xml");

  if(log4jXml == null)
    throw new IllegalStateException("my-log4j.xml not found");

  DOMConfigurator.configure(log4jXml);
}
```

- You use Log4J loggers in you webapp's servlet as well as the class in the
  other deployment.

- You invoke your servlet at `http://localhost:8080/deploymentA/test?name=Kyle`,
  which will cause logging messages to be created from both the servlet and the
  class that lives in the other deployment (`deploymentB`).

Questions:

- Does adding a dependency to another deployment pull in its implicit
  dependencies, possibly invalidating the exclusions already defined in your
  `jboss-deployment-structure.xml'?
- Should the class that lives in `deploymentB` use the logging context set up by
  `deploymentA`, or use the logging context utilized by `deploymentB`?
- Does the type of deployment (e.g. ear, war, jar, rar) change this behavior?

**Note**: I have done some simple scripting to build, deploy, run the test, and
print the last few lines from the logs in `runTest.sh`.
