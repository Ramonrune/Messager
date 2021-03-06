package com.mensagens.Messager.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	
	//matrixparam = http://localhost:8080/messager/webapi/injectdemo/annotations;param=true
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeaderValue") String customHeaderValue,
											@CookieParam("cookieName") String cookie){
		return "Matriz param: " + matrixParam + "Header param: " + customHeaderValue + " Cookie: " + cookie;
		
	}
	
	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo,
									   @Context HttpHeaders headers){
		String path = uriInfo.getAbsolutePath().toString(); 
		String cookies = headers.getCookies().toString();
		return "Path: " + path + " Cookie: " + cookies;
	}
	
}
