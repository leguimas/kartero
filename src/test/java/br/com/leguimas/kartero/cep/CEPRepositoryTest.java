package br.com.leguimas.kartero.cep;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.leguimas.kartero.TesteIntegrado;

public class CEPRepositoryTest extends TesteIntegrado {

	@BeforeClass
	public static void carregaDadosDeTeste() throws IOException, SQLException {
		TesteIntegrado.executaScript("cep.dados");
	}

	@Test
	public void consultaCEP() {
		CEPRepository repositorioDeCeps = new CEPRepository();
		CEP cepEncontrado = repositorioDeCeps.consultaCEP("13045135");

		assertNotNull(cepEncontrado);
		assertEquals("13045135", cepEncontrado.obtemCEP());
		assertEquals("Rua Sargento Luis de Morais", cepEncontrado.obtemLogradouro());
		assertEquals("Jardim São Vicente", cepEncontrado.obtemBairro());
		assertEquals("Campinas", cepEncontrado.obtemCidade());
		assertEquals("SP", cepEncontrado.obtemUf());
	}

	@Test
	public void consultaCEPInexistente() {
		CEPRepository repositorioDeCeps = new CEPRepository();
		assertNull(repositorioDeCeps.consultaCEP("00000000"));
	}

}
