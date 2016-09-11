package first;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/first")
public class FirstWebService {

	@GET
	@Produces(MediaType.TEXT_XML)		//consumes
	public String helloWorldXML(){
		
		String response = "<?xml version='1.0'?>" +
			"<hello>HelloWorld XML</hello>";
		return response;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)		//consumes
	public String helloWorldJSON(){
		
		String response = 	"<h1>HelloWorld</h1>  "	+
							"<strong>Username:</strong><input type = text name = username/>  "	+
							"<strong>Password:</strong><input type = password name = password/>  "   +
							"<input type = button value = Submit>";
		return response;
	}
}
