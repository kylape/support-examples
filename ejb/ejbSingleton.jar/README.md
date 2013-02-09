##Simple EJB Singleton Example

Shows how to create an EJB singleton with a method that's executed upon deployment (via `@PostConstruct`).  Upon deployment, you should see a log message like this:

    [com.redhat.gss.ejb.EjbSingleton] (MSC service thread 1-11) Hello, world!
