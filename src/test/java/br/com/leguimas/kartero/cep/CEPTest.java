package br.com.leguimas.kartero.cep;

import static org.junit.Assert.assertEquals;

import javax.json.JsonObject;

import org.junit.Test;

public class CEPTest {

	private static final String DEFAULT_CEP = "13045135";

	@Test
	public void construtorTest() {
		assertEquals(DEFAULT_CEP, new CEP("13045135").obtemCEP());
		assertEquals(DEFAULT_CEP, new CEP("13045-135").obtemCEP());
		assertEquals(DEFAULT_CEP, new CEP("1304D5-1E3F5D").obtemCEP());
		assertEquals("", new CEP("SOMENTE CARACATERES").obtemCEP());
	}

	@Test
	public void emFormatoJsonTest() {
		CEP cep = new CEP(DEFAULT_CEP);

		JsonObject cepEmJson = cep.emFormatoJson();
		assertEquals("13045135", cepEmJson.getString("cep"));
		assertEquals("", cepEmJson.getString("logradouro"));
		assertEquals("", cepEmJson.getString("bairro"));
		assertEquals("", cepEmJson.getString("cidade"));
		assertEquals("", cepEmJson.getString("uf"));
	}

}