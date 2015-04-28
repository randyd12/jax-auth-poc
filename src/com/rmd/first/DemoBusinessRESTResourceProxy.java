package com.rmd.first;
 
import java.io.Serializable;
import javax.ejb.Local;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Local
public interface DemoBusinessRESTResourceProxy extends Serializable {

    public Response login(
        @Context HttpHeaders httpHeaders,
        @FormParam( "username" ) String username,
        @FormParam( "password" ) String password );

    public Response demoGetMethod();

    public Response demoPostMethod();

    public Response logout(
        @Context HttpHeaders httpHeaders
    );
}