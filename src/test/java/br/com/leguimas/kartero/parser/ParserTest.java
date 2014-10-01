package br.com.leguimas.kartero.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.leguimas.kartero.TesteIntegrado;
import br.com.leguimas.kartero.cep.CEP;
import br.com.leguimas.kartero.cep.CEPRepository;
import br.com.leguimas.kartero.importer.entity.Bairro;
import br.com.leguimas.kartero.importer.entity.Localidade;
import br.com.leguimas.kartero.importer.entity.Logradouro;
import br.com.leguimas.kartero.importer.parser.CorreiosParser;

public class ParserTest extends TesteIntegrado {

	@BeforeClass
	public static void carregaDadosDeTeste() throws IOException, SQLException {
		TesteIntegrado.executaScript("base.dados");
	}

	@Test
	public void testParser() {
		String basePath = "./src/test/resources/baseExemplo/Base_eDNE/Basico/Delimitado";

		CorreiosParser parser = new CorreiosParser();
		parser.parseFiles(basePath);

		assertLocalidades(parser);
		assertBairros(parser);
		assertLogradouros(parser);

		parser.saveToBase();

		assertResultFromImport();
		assertLocalidadeCepUnico();
	}

	private void assertLocalidadeCepUnico() {
		CEPRepository repositorioDeCeps = new CEPRepository();
		CEP cepEncontrado = repositorioDeCeps.consultaCEP("69945000");

		assertNotNull(cepEncontrado);
		assertEquals("69945000", cepEncontrado.obtemCEP());
		assertEquals("", cepEncontrado.obtemLogradouro());
		assertEquals("", cepEncontrado.obtemBairro());
		assertEquals("Acrelandia", cepEncontrado.obtemCidade());
		assertEquals("AC", cepEncontrado.obtemUf());
	}

	private void assertResultFromImport() {
		CEPRepository repositorioDeCeps = new CEPRepository();
		CEP cepEncontrado = repositorioDeCeps.consultaCEP("69900058");

		assertEquals("69900058", cepEncontrado.obtemCEP());
		assertEquals("Rua Arlindo Porto Leal", cepEncontrado.obtemLogradouro());
		assertEquals("Centro", cepEncontrado.obtemBairro());
		assertEquals("Rio Branco", cepEncontrado.obtemCidade());
		assertEquals("AC", cepEncontrado.obtemUf());
	}

	private void assertLogradouros(CorreiosParser parser) {
		List<Logradouro> logradouros = parser.getLogradouros();
		assertNotNull(logradouros);
		assertEquals(87, logradouros.size());

		// Testa 3 logradouros pegos ao acaso devido a quantidade de dados na
		// base de exemplo

		Logradouro logradouro = logradouros.get(9);
		assertEquals(491, (int) logradouro.getLogNu());
		assertEquals("AC", logradouro.getUfeSg());
		assertEquals(16, (int) logradouro.getLocNu());
		assertEquals(55386, (int) logradouro.getBaiNuIni());
		assertNull(logradouro.getBaiNuFim());
		assertEquals("Pernambuco", logradouro.getLogNo());
		assertEquals("- ate 484/485", logradouro.getLogComplemento());
		assertEquals("69900306", logradouro.getCep());
		assertEquals("Rua", logradouro.getTloTx());
		assertEquals('S', (char) logradouro.getLogStaTlo());
		assertEquals("R Pernambuco", logradouro.getLogNoAbrev());

		logradouro = logradouros.get(63);
		assertEquals(72044, (int) logradouro.getLogNu());
		assertEquals("DF", logradouro.getUfeSg());
		assertEquals(1778, (int) logradouro.getLocNu());
		assertEquals(1193, (int) logradouro.getBaiNuIni());
		assertNull(logradouro.getBaiNuFim());
		assertEquals("QE 1 Bloco A", logradouro.getLogNo());
		assertNull(logradouro.getLogComplemento());
		assertEquals("71020011", logradouro.getCep());
		assertEquals("Quadra", logradouro.getTloTx());
		assertEquals('N', (char) logradouro.getLogStaTlo());
		assertEquals("Q QE", logradouro.getLogNoAbrev());

		logradouro = logradouros.get(86);
		assertEquals(846589, (int) logradouro.getLogNu());
		assertEquals("ES", logradouro.getUfeSg());
		assertEquals(1847, (int) logradouro.getLocNu());
		assertEquals(1454, (int) logradouro.getBaiNuIni());
		assertNull(logradouro.getBaiNuFim());
		assertEquals("Natalino Francisco da Silva", logradouro.getLogNo());
		assertNull(logradouro.getLogComplemento());
		assertEquals("29700559", logradouro.getCep());
		assertEquals("Rua", logradouro.getTloTx());
		assertEquals('S', (char) logradouro.getLogStaTlo());
		assertEquals("R Natalino F da Silva", logradouro.getLogNoAbrev());
	}

	private void assertBairros(CorreiosParser parser) {
		List<Bairro> bairros = parser.getBairros();
		assertNotNull(bairros);
		assertEquals(102, bairros.size());

		// Testa 3 bairros pegos ao acaso devido a quantidade de dados na base
		// de exemplo
		Bairro bairro = bairros.get(10);
		assertEquals(1453, bairro.getBaiNu());
		assertEquals("ES", bairro.getUfeSg());
		assertEquals(1847, bairro.getLocNu());
		assertEquals("Centro", bairro.getBaiNo());
		assertNull(bairro.getBaiNoAbrev());

		bairro = bairros.get(17);
		assertEquals(48451, bairro.getBaiNu());
		assertEquals("DF", bairro.getUfeSg());
		assertEquals(1778, bairro.getLocNu());
		assertEquals("Setor Sul (Planaltina)", bairro.getBaiNo());
		assertEquals("St Sul (Planaltina)", bairro.getBaiNoAbrev());

		bairro = bairros.get(100);
		assertEquals(55467, bairro.getBaiNu());
		assertEquals("AC", bairro.getUfeSg());
		assertEquals(16, bairro.getLocNu());
		assertEquals("Loteamento Joafra", bairro.getBaiNo());
		assertEquals("Lot Joafra", bairro.getBaiNoAbrev());
	}

	private void assertLocalidades(CorreiosParser parser) {
		List<Localidade> localidades = parser.getLocalidades();
		assertNotNull(localidades);
		assertEquals(6, localidades.size());

		Localidade loc = localidades.get(0);
		assertEquals(1, (int) loc.getLocNu());
		assertEquals("AC", loc.getUfeSg());
		assertEquals("Acrelandia", loc.getLocNo());
		assertEquals("69945000", loc.getCep());
		assertEquals('0', (char) loc.getLocInSit());
		assertEquals('M', (char) loc.getLocInTipoLoc());
		assertNull(loc.getLocNuSub());
		assertEquals("Acrelandia", loc.getLocNoAbrev());
		assertEquals("1200013", loc.getMunNu());

		loc = localidades.get(1);
		assertEquals(15, (int) loc.getLocNu());
		assertEquals("AC", loc.getUfeSg());
		assertEquals("Porto Walter", loc.getLocNo());
		assertEquals("69982000", loc.getCep());
		assertEquals('0', (char) loc.getLocInSit());
		assertEquals('M', (char) loc.getLocInTipoLoc());
		assertNull(loc.getLocNuSub());
		assertEquals("Pto Walter", loc.getLocNoAbrev());
		assertEquals("1200393", loc.getMunNu());

		loc = localidades.get(2);
		assertEquals(16, (int) loc.getLocNu());
		assertEquals("AC", loc.getUfeSg());
		assertEquals("Rio Branco", loc.getLocNo());
		assertNull(loc.getCep());
		assertEquals('1', (char) loc.getLocInSit());
		assertEquals('M', (char) loc.getLocInTipoLoc());
		assertNull(loc.getLocNuSub());
		assertEquals("Rio Branco", loc.getLocNoAbrev());
		assertEquals("1200401", loc.getMunNu());

		loc = localidades.get(3);
		assertEquals(1778, (int) loc.getLocNu());
		assertEquals("DF", loc.getUfeSg());
		assertEquals("Brasilia", loc.getLocNo());
		assertNull(loc.getCep());
		assertEquals('1', (char) loc.getLocInSit());
		assertEquals('M', (char) loc.getLocInTipoLoc());
		assertNull(loc.getLocNuSub());
		assertEquals("Brasilia", loc.getLocNoAbrev());
		assertEquals("5300108", loc.getMunNu());

		loc = localidades.get(4);
		assertEquals(1811, (int) loc.getLocNu());
		assertEquals("ES", loc.getUfeSg());
		assertEquals("Angelo Frechiani", loc.getLocNo());
		assertNull(loc.getCep());
		assertEquals('2', (char) loc.getLocInSit());
		assertEquals('D', (char) loc.getLocInTipoLoc());
		assertEquals(1847, (int) loc.getLocNuSub());
		assertEquals("Angelo Frechiani", loc.getLocNoAbrev());
		assertNull(loc.getMunNu());

		loc = localidades.get(5);
		assertEquals(1847, (int) loc.getLocNu());
		assertEquals("ES", loc.getUfeSg());
		assertEquals("Colatina", loc.getLocNo());
		assertNull(loc.getCep());
		assertEquals('1', (char) loc.getLocInSit());
		assertEquals('M', (char) loc.getLocInTipoLoc());
		assertNull(loc.getLocNuSub());
		assertEquals("Colatina", loc.getLocNoAbrev());
		assertEquals("3201506", loc.getMunNu());
	}
}
