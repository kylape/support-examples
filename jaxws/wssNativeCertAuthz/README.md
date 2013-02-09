##JBossWS Native WS-Security x509 profile & authorization

This is an example of JBossWS Native's x509 support, based off of a test case made for JBWS-2116.  In addition, the certificate is used to assign roles to the principal and authorize calls to the EJB.

To build and deploy the example, run the script `buildAndDeploy.sh`.  To run the test client, `cd` to `wssNativeCertAuthz.jar` and run `ant test`.  This will deploy an EJB and a .sar.  The .sar will deploy the `JBossWSCert` security domain.

There are two user defined, `alice` and `john`.  `alice` has the roles `friend` and `girlfriend`, but `john` just has the role `friend`.  The test client attempts to call the two endpoint methods `echo` and `echo2` with each user.  `echo` requires `friend` and `echo2` requires `girlfriend`.  Therefore the call to `echo2` using the user `john` will fail, but all other calls will succeed.
