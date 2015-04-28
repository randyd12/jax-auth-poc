package com.rmd.first;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/")
public class DemoConfig extends ResourceConfig  {
	public DemoConfig() {
        super(DemoBusinessRESTResource.class);
        register(RolesAllowedDynamicFeature.class);
        register(DemoRESTRequestFilter.class);
	}
	
}
