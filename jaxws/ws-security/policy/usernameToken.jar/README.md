#UsernameToken example

- Includes static WSDL in deployment
- WSDL includes UT policy
- Uses built-in `other` security domain

Run this to set up security domain:

```
$JBOSS_HOME/bin/add-user -a klape "RedHat13#"
```

Add this line to `$JBOSS_HOME/standalone/configuration/application-roles.properties`:

```
klape=hello
```

Run `ant deploy` and then `ant test`.
