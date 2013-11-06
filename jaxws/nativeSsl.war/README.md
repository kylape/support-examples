#JBossWS Native SSL client example

Run `ant deploy` and then `ant test` to test the SSL connection.  The
`server.xml.fragment` file shows how I've configured JBossWeb.  I also generated
two keystores, `jbossweb.keystore` and `client.keystore`.  The former goes in
`JBOSS_HOME/server/$PROFILE/conf` and the latter goes in the classpath root of
the client.
