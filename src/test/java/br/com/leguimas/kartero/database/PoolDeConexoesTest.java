package br.com.leguimas.kartero.database;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.leguimas.kartero.TesteIntegrado;

public class PoolDeConexoesTest extends TesteIntegrado {

	@Test
	@Ignore
	public void obtemConexaoTest() throws ClassNotFoundException, SQLException, IOException {
		Connection conexao = PoolDeConexoes.obtemConexao();
		PreparedStatement consulta = conexao.prepareStatement("SELECT * FROM UNNEST(SEQUENCE_ARRAY(10, 12, 1))");
		assertNotNull(consulta.executeQuery());
	}

}
