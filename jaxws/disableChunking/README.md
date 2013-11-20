##Shared CXF Config (Chunking)

In JBoss EAP 6, it's not very easy to disable chunking at the system level for
JAX-WS clients.  To do this, you'll need to:

- Install Spring as a shared module in `org.springframework.spring:main`.  
- Copy the module in this project to your JBoss modules folder
  (`com.redhat.gss.cxf.shared-cxf-config`).  
- Include a `jbossws-cxf.xml` in your deployment (either
  `WEB-INF/jbossws-cxf.xml` for wars or `META-INF/jbossws-cxf.xml` for
  EJB-jars).  This will trigger JBossWS to use the Spring-based CXF bus.

The `cxf.xml` in the sample shared CXF config simply disabled chunking.
