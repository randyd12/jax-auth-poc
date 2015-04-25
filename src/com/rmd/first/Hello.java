package com.rmd.first;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//tutorial:  http://www.vogella.com/tutorials/REST/article.html
//maven: added org.glassfish.jersey.containers
//The url is:
//http://localhost:8080/jax-auth-poc/rest/hello

//fiddler request XML
//GET http://localhost:8080/jax-auth-poc/rest/hello HTTP/1.1
//User-Agent: Fiddler
//Host: localhost:8080
//Accept: text/xml;charset=UTF-8


//fiddler request html
//GET http://localhost:8080/jax-auth-poc/rest/hello HTTP/1.1
//User-Agent: Fiddler
//Host: localhost:8080
//Accept: text/html;charset=UTF-8

//fiddler request text
//GET http://localhost:8080/jax-auth-poc/rest/hello HTTP/1.1
//User-Agent: Fiddler
//Host: localhost:8080
//Accept: text/html;charset=UTF-8



// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
    return "Hello Jersey";
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }

} 