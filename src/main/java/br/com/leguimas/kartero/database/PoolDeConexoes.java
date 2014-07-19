package br.com.leguimas.kartero.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import br.com.leguimas.kartero.KarteroException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class PoolDeConexoes {

	private static final String ARQUIVO_DE_CONFIGURACOES = "kartero.database";

	private static BoneCP poolDeConexoes;

	public static Connection obtemConexao() {

		if (poolDeConexoes == null) {
			try {
				iniciaPoolDeConexoes();
			} catch (ClassNotFoundException | IOException | SQLException e) {
				throw new KarteroException(e, "Problemas na inicializacao do pool de conexoes: " + e.getMessage());
			}
		}

		Connection conexao = null;
		try {
			conexao = poolDeConexoes.getConnection();
		} catch (SQLException e) {
			throw new KarteroException(e, "Problemas na obtencao de conexoes: " + e.getMessage());
		}

		return conexao;
	}

	private static void iniciaPoolDeConexoes() throws IOException, ClassNotFoundException, SQLException {
		Properties arquivoDeConfiguracoes = new Properties();
		try {
			arquivoDeConfiguracoes.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(ARQUIVO_DE_CONFIGURACOES));
		} catch (FileNotFoundException e) {
			throw new KarteroException(e, "Nao foi possivel encontrar o arquivo de configuracoes ("
					+ ARQUIVO_DE_CONFIGURACOES + "). Verifique se o mesmo encontra-se no classpath da aplicacao. ");
		}

		BoneCPConfig configuracoes = new BoneCPConfig();
		Class.forName(arquivoDeConfiguracoes.getProperty("kartero.classeJDBC"));

		configuracoes.setJdbcUrl(arquivoDeConfiguracoes.getProperty("kartero.urlJDBC"));
		configuracoes.setUsername(arquivoDeConfiguracoes.getProperty("kartero.usuario"));
		configuracoes.setPassword(arquivoDeConfiguracoes.getProperty("kartero.senha"));
		configuracoes.setMinConnectionsPerPartition(new Integer(arquivoDeConfiguracoes
				.getProperty("kartero.minimoDeConexoes")));
		configuracoes.setMaxConnectionsPerPartition(new Integer(arquivoDeConfiguracoes
				.getProperty("kartero.maximoDeConexoes")));
		configuracoes.setIdleConnectionTestPeriodInSeconds(10);

		poolDeConexoes = new BoneCP(configuracoes);
	}
}
