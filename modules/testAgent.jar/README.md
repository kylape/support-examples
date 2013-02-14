##Dummy Java Agent Example

This is a very simple Java agent implementation.  All it will do is initialize
logging in its `premain` method and try to log a few messages.  Install it by
adding the `-javaagent:testAgent.jar` property to your JVM invocation.  If
you're using JBoss AS7/EAP 6, this would be in the `bin/standalone.conf` or
`bin/domain.conf`.
