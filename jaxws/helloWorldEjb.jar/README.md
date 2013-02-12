###EJB Web Service Endpoint and Client

The test client will run four threads in parallel, each making 10 requests.  The
endpoint will print a message each time a new endpoint implementation instance
is created, showing that the EJB is pooled.
