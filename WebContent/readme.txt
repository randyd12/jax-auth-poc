
Setup:
web.xml must have call to rest class and configuration class
Configuration class gets executed with web server starts

Application usage flow:

1) Call login REST method with user name and password (this method is annotated with @PermitAll)


1.5) Note: the Request filter fires before the REST method, we pass through without setting security context
if they are calling login.

2) login method uses the DemoAuthenticator class to validate credentials and create an auth token.  This 
auth token is sent back on the http header via the Response Filter.
-in the real implementation we will do a database lookup here to validate credentials and return a JWT (Java
Web Token with claims like role).

3) The client then makes another call for a protected REST method and sends the auth token on the HTTP header.

4) The request filter validates the token and creates a security context.
-in the real implementation we'll be validating the JWT and if valid trusting the claims.

5) The call then continues to the REST method and the security context is then used by Jersey to check 
against the @RolesAllowed annotation.
