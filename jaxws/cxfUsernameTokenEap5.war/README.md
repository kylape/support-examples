###CXF UsernameToken example

This example project is primarily an example of how to configure CXF to take a
UsernameToken and authenticate/authorize it (i.e. build a Subject) using JAAS.

Take a look at `jbossws-cxf.xml` for the meat of the configuration.  The
`SubjectCreatingInterceptor` is a CXF interceptor included in JBossWS that
integrates the CXF `WSS4JInInterceptor` with JAAS.  It invoke the JAAS security
domain configured in `jboss-web.xml` to build the `Subject` based on the
UsernameToken credentials.

Optionally, you can use the `SimpleAuthorizingInterceptor` provided by the CXF
runtime to ensure the `Subject` built by the JAAS security domain is allowed to
invoke the requested operation.  Note that you have to define the role-method
mapping in `jbossws-cxf.xml`.

This example uses the `JBossWS` security domain, which is included in EAP 5 by
default.  You'll need to comment out the `kermit` user in
`$JBOSS_HOME/sever/default/conf/props/jbossws-users.propertes`.
