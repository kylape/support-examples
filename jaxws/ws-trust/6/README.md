###Picketlink WS-Trust STS example

This is an example of how to set up a WS-Trust scenario using Picketlink STS and JBossWS.
This example is for EAP 6.1+.

High level flow of request:

```
curl -> servlet -> sts -> servlet -> endpoint -> servlet -> curl
```

Endpoint:

```
request -> SAML2Handler -> WSAuthenticationHandler -> SAML2STSLoginModule -> STS (validate) -> SAML2STSLoginModule -> endpoint impl -> SAML2Handler -> response
```

To install:

- Set `$JBOSS\_HOME` and make sure `ant` is on your `PATH`.
- I used the STS provided by the Picketlink quickstart project [found here](https://github.com/picketlink2/picketlink-quickstarts).

  Once in the root folder of the git project, I check out the `v2.1.6.Final`
  tag and then nagivate to `ws-trust/picketlink-sts` to build the STS using
  `mvn install`.  I take the `picketlink-sts-2.1.6.Final-jboss-as7.war` from
  the target folder and deploy it to JBoss.

  I changed the security domain used by the STS from
  `picketlink-sts` to `other` and added a user to this domain via the
  `add-user.sh` script in the bin directory (I used admin/admin).  This simplified
  the deployment a bit.

- I added the 'picketlink-sts' security domain that will be used by my target
  endpoint (i.e. Service Provider (SP)):

  ```
  <security-domain name="picketlink-sts" cache-type="default">
      <authentication>
          <login-module code="org.picketlink.identity.federation.bindings.jboss.auth.SAML2STSLoginModule" 
                        flag="required" 
                        module="org.picketlink">
              <module-option name="configFile" value="/example-sts-client.properties"/>
          </login-module>
      </authentication>
  </security-domain>
  ```
  
  The config file is in my target endpoint's root classpath (i.e.
  `WEB-INF/classes`).

- Build and deploy `sts-client.war` by running `cd sts-client.war; ant deploy`
- Run the command `curl http://localhost:8080/sts-client/client?name=Kyle`, which will invoke the client servlet.
