## Example use of `@WebServiceRef`

This example exhibits several things:

- How to use a JAX-WS client in a multithreaded environment
- How to use `@WebServiceRef`
- How to create a JAX-WS client using JBoss Modules (in the ant script)
- Basic EJB and POJO JAX-WS endpoints

The example consists of:

- A standalone test client
- An SLSB endpoint that invokes another endpoint
- A simple POJO endpoint (that happens to bind a value into JNDI)

To run:

```
export JBOSS_HOME=<your jboss>
ant deploy
# wait a few seconds for it to deploy
ant test
```

This will create 50 threads that will invoke the client endpoint a total of
50000 times.  This in turns invokes the POJO endpoint 5000 times.
