###MDB Example

This is an example of an MDB and a couple of message producers.  There are three subdeploments:

- `mdb.jar`: the MDB packaged in an EJB-jar
- `mdbClient.jar`: a SLSB with a method that produces an `ObjectMessage`.
- `mdbClient.war`: A JAX-WS web service that also produces an `ObjectMessage`.
- `mdb-api.jar`: Just contains the class definition that is passed in the `ObjectMessage`.

The reason there are two message producers is to show that both WARs and
EJB-jars can send an object using a shared class (from the `mdb-api.jar`).

To build, set `$JBOSS_HOME` and run `ant deploy` in the EAR project's root
folder. The ant script builds the API jar first so it can be available on the
classpath when the other deployments are built.

The API jar is visible to all subdeployments since it's in the EAR's `lib`
folder, meaning classloading errors are minimized.
