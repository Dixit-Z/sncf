package updates;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/update")
public class UpdatesActions {
	
	@GET
	public Response getLines(){
		return Response.status(200).entity("OK").build();
	}

}
