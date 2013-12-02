#Log4J Performance Test

JBoss EAP 6 includes a different implementation of Log4J by default.  This
project will compare the performance of this implementation against the
original Log4J.  Simply run the `./test.sh` script and follow the directions to
run the test.

Requirements:

- Unix environment
- A clean version of JBoss EAP 6
- `JBOSS_HOME` environment variable set appropriately
- `ant` executable in the `PATH` environment
- `curl` executable in the `PATH` environment
