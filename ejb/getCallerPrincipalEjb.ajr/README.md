### Example of how to get caller's principal in an EJB

This EJB has one method that simply returns the caller's `Principal` via `EJBContext.getCallerPrincipal()`.  It's also exposed as a webservice using basic HTTP authentication.  The EJB uses the `other` security domain, so you can use the `add-user.sh` script to control access to the EJB.
