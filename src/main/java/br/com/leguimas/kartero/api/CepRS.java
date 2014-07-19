package br.com.leguimas.kartero.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.leguimas.kartero.cep.CEP;
import br.com.leguimas.kartero.cep.CEPRepository;

@Path("/cep")
public class CepRS {

	/**
	 * Dado um CEP, retorna as informa&ccedil;&otilde;es do endere&ccedil;o atre&ccedil;ado ao CEP em quest&atilde;o.
	 *
	 * @summary Consulta CEP
	 * @param cep
	 *            O CEP a ser procurado. Voc&ecirc; pode informar o CEP em qualquer formato. No processamento,
	 *            ser&atilde;o processados utilizados apenas os d&iacute;gitos num&eacute;ricos.
	 * @return Dados do endereço de um CEP.
	 * @statuscode 200 O CEP informado foi encontrado.
	 * @statuscode 404 Foi informado um CEP n&atilde;o cadastrado na base de dados.
	 */
	@GET
	@Path("/{cep}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Response consultaCep(@PathParam("cep") String cep) {
		CEPRepository repositorio = new CEPRepository();
		CEP cepEncontrado = repositorio.consultaCEP(cep);

		if (cepEncontrado == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(cepEncontrado.emFormatoJson().toString()).build();
	}
}
