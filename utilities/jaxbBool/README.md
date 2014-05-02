#JAXB Boolean test

Maven project to test the behavior of JAXB boolean unmarshalling in different
scenarios, specifically three.  To run, configure the `<jboss-home>` property in
the `pom.xml` and run these three commands:

Standalone usage:

```
mvn -Pjdk exec:exec
```

Standalone usage using JBoss libs:

```
mvn -Pjboss exec:exec
```

JAXB usage via JAX-WS endpoint:


```
mvn -Pservlet jboss-as:deploy exec:exec
```
