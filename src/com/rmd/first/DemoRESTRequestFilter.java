package com.rmd.first;

import com.rmd.first.DemoAuthenticator;
import com.rmd.first.DemoHTTPHeaderNames;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;





//import java.io.IOException;
import java.security.Principal;




//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
//import javax.ws.rs.ext.Provider;



import org.glassfish.jersey.message.filtering.SecurityEntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


@Provider
@PreMatching
//public class DemoRESTRequestFilter extends ResourceConfig implements ContainerRequestFilter {
public class DemoRESTRequestFilter implements ContainerRequestFilter {
	
//	public DemoRESTRequestFilter() {
//	    // Register entity-filtering security feature.
//	    //register(SecurityEntityFilteringFeature.class);
//	    
//        super(DemoBusinessRESTResource.class);
//        register(RolesAllowedDynamicFeature.class);
//        register(DemoRESTRequestFilter.class);
//	}
//	
    private final static Logger log = Logger.getLogger( DemoRESTRequestFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {
 
    	
    	
    	
    	
    	//Check the request to see if it has our security token,
    	//if it does then we can trust the claims in the token and
    	//set the security context.  This token will be replaced with JWT (Java Web Tokens).
    	
    	
    	
      String path = requestContext.getUriInfo().getPath();
      log.info( "Filtering request path: " + path );

      // IMPORTANT!!! First, Acknowledge any pre-flight test from browsers for this case before validating the headers (CORS stuff)
      if ( requestContext.getRequest().getMethod().equals( "OPTIONS" ) ) {
      	requestContext.abortWith( Response.status( Response.Status.OK ).build() );

          return;
      }

      // Then check is the service key exists and is valid.
      DemoAuthenticator demoAuthenticator = DemoAuthenticator.getInstance();
      String serviceKey = requestContext.getHeaderString( DemoHTTPHeaderNames.SERVICE_KEY );

      if ( !demoAuthenticator.isServiceKeyValid( serviceKey ) ) {
          // Kick anyone without a valid service key
      	requestContext.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
          log.info( "Unauthorized - service key is NOT valid" );
          return;
      }

      Boolean securityTokenIsValid = false;
      
      // For any other methods besides login, the authToken must be verified
      if ( !path.contains( "demo-business-resource/login" ) ) {
          String authToken = requestContext.getHeaderString( DemoHTTPHeaderNames.AUTH_TOKEN );

          // if it isn't valid, just kick them out.
          if ( !demoAuthenticator.isAuthTokenValid( serviceKey, authToken ) ) {
          	requestContext.abortWith( Response.status( Response.Status.UNAUTHORIZED ).build() );
          }
          else
          {
        	  securityTokenIsValid = true;
          }
      }

      if (securityTokenIsValid)
      {
    	  
    	  //NOTE:  In the real world the JWT token will have claims like
    	  //user name and role.  We would extract those claims and put them
    	  //into the security context.  For now just hard coded.
    	
    	
    	requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
            	
                return new Principal() {
                    @Override
                    public String getName() {
                        return "Jersey";
                    }
                };
            }

            @Override
            public boolean isUserInRole(final String role) {
                return "manager".equals(role);
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    	
      }
    	    
    }
}