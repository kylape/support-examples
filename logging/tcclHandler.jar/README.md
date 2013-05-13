# TCCLHandler

This custom logging `Handler` will write log message to a different log
depending on the TCCL.  Specficially, it will pull the module identifier from
the `ModuleClassLoader` to see what deployment is currently executing.

Sample configuration:

```
<custom-handler name="TCCL" class="com.redhat.gss.logging.TcclHandler" module="com.redhat.gss.logging">
    <formatter>
        <pattern-formatter pattern="%d{HH:mm:ss,SSS} %-5p [%c] (%t) %s%E%n"/>
    </formatter>
    <properties>
        <property name="dirName" value="/Users/klape/jboss/product-distributions3/standalone/log"/>
        <property name="logNames" value="helloWorld:a"/>
    </properties>
</custom-handler>
```

Note that I removed the default `server.log` handler from my JBoss logging
config because I coded my custom handler to write to server.log.  You can
choose to modify this behavior if desired.  I also chose regular
`FileHandlers`, so there will be no log rotation; you can also change this as
you desire.

The value of the `logNames` option is a comma separated list of colon-delimited
pairs, where each pair is a key/value mapping a deployment name (without the
suffix) to a file name (also without the suffix).  So in the configuration
above, any log messages coming from `helloWorld.war` will be directed to
`standalone/log/a.log`.  There is a potential problem if you have two
deployments with the same name but different suffixes, e.g. `helloWorld.jar`
and `helloWorld.war`.  Subdeployments may not be handled very gracefully,
either, but this is a potential future improvement.
