# Custom Validation in CXF

This is an example of how to install a custom validation in JAXB via CXF
without using Spring.  This requires the use of a custom CXF interceptor that
adds a live instance of the custom `ValidationEventHandler` to the CXF Message
map.

To run the test, run `./request.sh`, which will use `curl` to invoke the
endpoint (`localhost` assumed) with an extra element in the SOAP body as a child
of the wrapper element.  Normally JAXB would return a SOAP fault, but with the
custom validation handler included in this project installed, you simply get a
warning message in the log:

```
17:49:24,282 WARN  [MyValidationEventHandler] unexpected element (uri:"", local:"arg1"). Expected elements are <{}arg0>
```
