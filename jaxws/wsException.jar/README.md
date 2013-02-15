###Simple JAX-WS exception example

One SLSB endpoint named `TestWs`.  The only method defined simply throws an exception.  If using CXF, the `LoggingFeature` is enabled so you can see how the exception is marshalled into a SOAP fault.

This example shows how the property order can be wrong using CXF if you have properties in multiple classes in the exception hierarchy.
