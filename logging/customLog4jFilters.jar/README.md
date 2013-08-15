#Custom Log4J Filters

Currently there's only one custom Log4J filter:
`ExceptionScanningPatternFilter`. This filter will scan exception messages and
stack frames for the pattern in addition to the log message itself.

Install like this inside an appender:

```
<filter class="com.redhat.gss.logging.ExceptionScanningPatternFilter">
    <param name="StringToMatch" value="gss" />
    <param name="AcceptOnMatch" value="true" />
</filter>
<filter class="org.apache.log4j.varia.DenyAllFilter" /> 
```

To build the project, run `ant` and copy the jar to
`$JBOSS_HOME/server/$PROFILE/lib`.

There is an associated test included in the project.  To run it, run `ant
deploy` and then `./request.sh`.
