# RESTEasy Mime4j Example

This is a simple JAX-RS example that utilized the RESTEasy integration with
MIME4J.  Deploy the project and run the following command:

```
curl -F "name=Kyle" -F "file=@someFile.txt;type=text/plain" http://localhost:8080/mime/rest
```

Make sure `someFile.txt` actually represents a real file.

This example also includes a workrouand for a RESTEasy issue where temporary
files created by mime4j are unable to be deleted and build up on Windows
machines.
