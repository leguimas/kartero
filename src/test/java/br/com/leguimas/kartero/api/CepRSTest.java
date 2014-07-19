package br.com.leguimas.kartero.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mortbay.jetty.HttpStatus;

import br.com.leguimas.kartero.TesteIntegrado;

public class CepRSTest extends TesteIntegrado {

	@BeforeClass
	public static void carregaDadosDeTeste() throws IOException, SQLException {
		TesteIntegrado.executaScript("cep.dados");
	}

	@Test
	public void testaConsultaCEP() throws ClientProtocolException, IOException {
		HttpResponse resposta = realizaConsultaDeCEP("13045-135");
		assertEquals(HttpStatus.ORDINAL_200_OK, resposta.getStatusLine().getStatusCode());

		String conteudoDaResposta = EntityUtils.toString(resposta.getEntity());
		assertTrue(conteudoDaResposta.startsWith("{"));
		assertTrue(conteudoDaResposta.endsWith("}"));
		assertTrue(conteudoDaResposta.contains("\"cep\":\"13045135\""));
		assertTrue(conteudoDaResposta.contains("\"logradouro\":\"Rua Sargento Luis de Morais\""));
		assertTrue(conteudoDaResposta.contains("\"bairro\":\"Jardim São Vicente\""));
		assertTrue(conteudoDaResposta.contains("\"cidade\":\"Campinas\""));
		assertTrue(conteudoDaResposta.contains("\"uf\":\"SP\""));
	}

	@Test
	public void testaConsultaCEPInexistente() throws ClientProtocolException, IOException {
		HttpResponse resposta = realizaConsultaDeCEP("13000-000");
		assertEquals(HttpStatus.ORDINAL_404_Not_Found, resposta.getStatusLine().getStatusCode());
	}

	private HttpResponse realizaConsultaDeCEP(String cep) throws IOException, ClientProtocolException {
		HttpClient requisicao = new DefaultHttpClient();
		HttpResponse resposta = requisicao.execute(new HttpGet("http://localhost:8080/api/cep/" + cep));
		return resposta;
	}

}
