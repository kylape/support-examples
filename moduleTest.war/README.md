###Classloading debugger web service

Methods used to figure out what module a class came from.  A servlet is also included in case you need to debug webservice classloading. 

The web service is pretty straightforward to use (use SoapUI), but the servlet need a bit of explanation.  The servlet commands are invoked like so:

    http://localhost:8080/moduleTest/moduleTest?command=getModuleOfClass&className=javax.xml.ws.Service

Use the `testSaajMessageFactory` method to figure out how to make a deployment in EAP 6 use the SAAJ implementation found in the JDK.

Make sure to copy the `sun.internal.saaj` module to your `$JBOSS_HOME/modules` directory.
