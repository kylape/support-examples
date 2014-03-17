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

- Set `$JBOSS_HOME` and make sure `ant` is on your `PATH`.
- I used the STS provided by the Picketlink quickstart, which can be found in the EAP 6.2 quickstarts.
- Run the `configure-security-domain.cli` script found in the quickstart.
- Deploy the STS using `mvn jboss-as:deploy`, just the same as the other quickstarts are deployed.
- I added the `ws-endpoint` security domain that will be used by my target
  endpoint (i.e. Service Provider (SP)):

  ```
  <security-domain name="ws-endpoint" cache-type="default">
      <authentication>
          <login-module code="org.picketlink.identity.federation.bindings.jboss.auth.SAML2STSLoginModule" 
                        flag="required" 
                        module="org.picketlink">
              <module-option name="configFile" value="/sts-client.properties"/>
          </login-module>
      </authentication>
  </security-domain>
  ```
  
  The STS client config file is in my target endpoint's root classpath (i.e.
  `WEB-INF/classes`).

- Build and deploy `sts-client.war` by running `cd sts-client.war; ant deploy`
- Run the command `curl http://localhost:8080/sts-client/client?name=Kyle`, which will invoke the client servlet.
