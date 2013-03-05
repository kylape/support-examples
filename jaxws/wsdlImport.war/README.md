###Example of web service with a `wsdl:import`

To see the WSDL with the `wsdl:import`, run this command:

```
curl http://localhost:8080/wsdlImport/hello?wsdl
```

To test the WSDL rewrite functionality of JBossWS regarding `wsdl:import`s,
change the `<wsdl-host>` value to `test.com`. Optionally you can also add a
mapping in your `/etc/hosts` file to map `127.0.0.1` to `test.com`.
