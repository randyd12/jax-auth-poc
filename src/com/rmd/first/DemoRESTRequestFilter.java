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


@Provider
@PreMatching
public class DemoRESTRequestFilter implements ContainerRequestFilter {

    private final static Logger log = Logger.getLogger( DemoRESTRequestFilter.class.getName() );

    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {
    	requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
            	
            	Integer x = 0;
            	x = 1;
            	
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