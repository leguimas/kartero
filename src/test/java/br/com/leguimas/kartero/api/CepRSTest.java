package br.com.leguimas.kartero.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.mortbay.jetty.HttpStatus;

import br.com.leguimas.kartero.TesteIntegrado;

public class CepRSTest extends TesteIntegrado {

	@Test
	public void testaConsultaCEP() throws ClientProtocolException, IOException {

		HttpClient requisicao = new DefaultHttpClient();
		HttpResponse resposta = requisicao.execute(new HttpGet("http://localhost:8080/api/cep/13045135"));

		assertEquals(HttpStatus.ORDINAL_200_OK, resposta.getStatusLine().getStatusCode());
	}

}
