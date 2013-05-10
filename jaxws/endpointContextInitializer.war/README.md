### Use of `javax.xml.ws.Endoint.publish()`

This is an example of how **NOT** to deploy a JAX-WS endpoint.
`javax.xml.ws.Endpoint.publish()` is not intended to be used inside an EE
container; it should only be used in standalone environments.
