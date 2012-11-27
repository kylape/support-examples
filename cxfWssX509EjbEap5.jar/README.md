###X509 WS-Security Example for CXF in EAP 5

This is an example of how to set up WS-Security X509 profile.  The client signs the message using alice's private key (via the WSS4J action `Timestamp Signature`), and the endpoint verifies the signature.  The endpoint also signs the response using bob's key, and the client verifies.  `ant deploy` to build and deploy and `ant test` to run the test client.
