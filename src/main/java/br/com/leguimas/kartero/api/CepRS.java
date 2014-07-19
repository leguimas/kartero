package br.com.leguimas.kartero.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/cep")
public class CepRS {

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok("pong").build();
	}

}
