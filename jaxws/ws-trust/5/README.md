###Picketlink WS-Trust STS example

This is an example of how to set up a WS-Trust scenario using Picketlink STS and JBossWS.
This example is geared for EAP 5.2+.

High level flow of request:

```
curl -> servlet -> sts -> servlet -> endpoint -> servlet -> curl
```

endpoint:

```
request -> SAML2Handler -> WSAuthenticationHandler -> SAML2STSLoginModule -> STS (validate) -> SAML2STSLoginModule -> endpoint impl -> SAML2Handler -> response
```

To install:

- Let's consider `$JBOSS_INSTALLATION` to be the root directory of your JBoss EAP 5.2 installation.  It contains `jboss-as`, `seam`, `picketlink`, etc.
- Set `$JBOSS_HOME` to `$JBOSS_INSTALLATION/jboss-as`.
- Set `$JBOSS_PROFILE` to your server profile name.  For instance if you want to use `$JBOSS_HOME/server/all`, then set `JBOSS_PROFILE=all`.  The default server is used if `$JBOSS_PROFILE` is not set.
- Enable the admin user.  Edit `$JBOSS_HOME/server/$JBOSS_PROFILE/conf/props/jmx-console-users.properties` and uncomment the line with `admin=admin`.
- Make sure `ant` is on your `$PATH`.
- Copy the jars `$JBOSS_INSTALLATION/picketlink/picketlink-federation/*.jar` to `$JBOSS_HOME/server/$JBOSS_PROFILE/lib`.
- Copy the directory `$JBOSS_INSTALLATION/picketlink/picketlink-quickstarts/picketlink-sts.war` to `$JBOSS_HOME/server/$JBOSS_PROFILE/deploy`.
- Build and deploy `picketlink-client.sar` by running `cd picketlink-client.sar; ant deploy`.
- Build and deploy `sts-client.war` by running `cd sts-client.war; ant deploy`.
- Start JBoss EAP 5.2.  Example: `$JBOSS_HOME/bin/run.sh -c $JBOSS_PROFILE`.
- Run the command `curl http://localhost:8080/sts-client/client?name=Kyle`, which will invoke the client servlet.
