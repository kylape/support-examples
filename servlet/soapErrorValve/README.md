Custom SOAP Error Valve

This simple project shows how you can configure JBossWeb (the servlet container
in JBoss) to return a SOAP response rather than HTML when returing HTTP 4xx and
5xx responses.  The solution extends the `ErrorReportValve`, and there are
different versions for EAP 5 and 6.

The `customeError.war` is just a simple endpoint that requires HTTP
authentication.  It's pretty easy to configure authentication that always fails
:).
