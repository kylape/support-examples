###How to Inject an Application Object in a JAX-RS Resource

This example shows what you need set up to inject a subclass of `javax.ws.rs.Application` into a resourse of provider (a provider example is not given here). `ant deploy` to build and deploy to JBoss and `curl http://localhost:8080/injecting-application/rest/` to invoke the test resource.
