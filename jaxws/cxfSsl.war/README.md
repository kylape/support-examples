#CXF SSL Client in JBoss EAP 6

This is a project to show how to set up a JBossWS CXF SSL client.  Among other
things, it also shows how you could automate the installation of an HTTPS
connection in EAP 6 and configure a standalone client to use JBoss LogManager.
This example will set up two-way authentication between the client and server.
Specifically running `./runClient.sh` will:

- Generate a client and server keystore
  - Put each others' certificates in each others' keystores
  - The client and server each use their respective keystore also as their
    truststore.
- Starts JBoss EAP 6
- Installs the HTTPS connector via the CLI
- Builds and deploys the endpoint
- Invokes the client
  - Normally starts up a standalone client using JBoss Modules
  - Use the `-s` option to the script to invoke the client within JBoss
- Undeploys the application
- Uninstalls the HTTPS connectors
- Shuts down JBoss
- Client logs are in `client.log` and server logs in `server.log`

**You must have `JBOSS_HOME` and `JAVA_HOME` set properly and you probably
shouldn't have an HTTPS connector already installed.**
