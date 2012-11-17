support-examples
================

Examples I've developed through the course of working cases.  Examples are all ant-based.  The ant script requires `$JBOSS_HOME` to be set.  Once set, you can build and deploy the project with `ant deploy`.  If there's a test client included in the project, it can be run via `ant test`.
 
###Simple EJB Singleton Example

Shows how to create an EJB singleton with a method that's executed upon deployment (via `@PostConstruct`).  Upon deployment, you should see a log message like this:

    [com.redhat.gss.ejb.EjbSingleton] (MSC service thread 1-11) Hello, world!
 
###Classloading debugger web service

Methods used to figure out what module a class came from.  A servlet is also included in case you need to debug webservice classloading. 

The web service is pretty straightforward to use (use SoapUI), but the servlet need a bit of explanation.  The servlet commands are invoked like so:

    http://localhost:8080/moduleTest/moduleTest?command=getModuleOfClass&className=javax.xml.ws.Service

Use the `testSaajMessageFactory` method to figure out how to make a deployment in EAP 6 use the SAAJ implementation found in the JDK.

Make sure to copy the `sun.internal.saaj` module to your `$JBOSS_HOME/modules` directory.
 
###Simple MTOM Example

This example takes binary data transferred via a MIME attachment and writes it to the filesystem using a random file name.  The included SoapUI project can be used to invoke the endpoint.  Note that you will need to set the attachment in SoapUI to a valid filename.  You will also need to modify the endpoint implementation class to point to a valid path to write the files to.
 
###Simple `ServletContextListener`

This is pretty much as basic as it gets.  Prints out "Context initilized!" upon deployment and that's it.
 
###SLF4J via Log4J in EAP 6

This deployment has a simple EJB with one `@PostConstruct`-annotated method.  This EJB also has two loggers, one from SLF4J and the other from Log4J.  The deployment contains a `log4j.xml` that defines a file appender to write all application logs to a file called `application.log`.  The `@PostConstruct`-annotated method will log something using both the SLF4J and Log4J loggers, thus testing to see if both log messages end up in application.log as desired.

This example will show how JBoss can configure complex logging requirments (e.g. SLF4J and Log4J in the same deployment) without the need for any special configuration or inclusion of additional libraries.
 
###Simple JAX-WS exception example

One SLSB endpoint named `TestWs`.  The only method defined simply throws an exception.  If using CXF, the `LoggingFeature` is enabled so you can see how the exception is marshalled into a SOAP fault.

This example shows how the property order can be wrong using CXF if you have properties in multiple classes in the exception hierarchy.
 
Various Utility Classes
-----------------------

This is just a collection of quick and dirty classes I've written to accomplish a specific task.

###IntString.java

This class will take a set of numbers delimited by ", " and turn it into a string.  This is useful when debugging in Eclipse and you are trying to figure out what data is in a stream.  Eclipse will present the data like so:

    [80, 79, 83, 84, 32, 47, 100, 101, ...

That's not very easy to read, so you can copy that stream in Eclipse to your clipboard and in Mac OS X run something like this:

    pbpaste | java IntString

