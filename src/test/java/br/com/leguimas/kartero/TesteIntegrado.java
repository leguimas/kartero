package br.com.leguimas.kartero;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.webapp.WebAppContext;

import br.com.leguimas.kartero.database.PoolDeConexoes;

public abstract class TesteIntegrado {

	private static org.mortbay.jetty.Server servidorWeb;

	private static org.hsqldb.Server bancoDeDados;

	@BeforeClass
	public static void iniciaAmbienteDeTestes() throws Exception {
		inicializaBancoDeDados();
		inicializaServidorWeb();
	}

	private static void inicializaServidorWeb() throws Exception {
		servidorWeb = new org.mortbay.jetty.Server(8080);
		servidorWeb.setStopAtShutdown(true);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setClassLoader(TesteIntegrado.class.getClassLoader());

		servidorWeb.addHandler(webAppContext);
		servidorWeb.start();
	}

	private static void inicializaBancoDeDados() throws IOException, AclFormatException {
		HsqlProperties configuracoes = new HsqlProperties();
		configuracoes.setProperty("server.database.0", "mem:");
		configuracoes.setProperty("server.dbname.0", "kartero");
		configuracoes.setProperty("server.port", "9001");

		bancoDeDados = new org.hsqldb.Server();
		bancoDeDados.setProperties(configuracoes);
		bancoDeDados.start();
	}

	@AfterClass
	public static void finalizaAmbienteDeTestes() throws Exception {
		finalizaServidorWeb();
		finalizaBancoDeDados();
	}

	private static void finalizaServidorWeb() throws Exception {
		servidorWeb.stop();
	}

	private static void finalizaBancoDeDados() {
		bancoDeDados.stop();
	}

	protected static void executaScript(String script) throws IOException, SQLException {
		Connection conexao = PoolDeConexoes.obtemConexao();

		ScriptRunner executor = new ScriptRunner(conexao);
		executor.setLogWriter(null);
		executor.setErrorLogWriter(null);

		Reader scriptParaExecucao = Resources.getResourceAsReader(script);
		executor.runScript(scriptParaExecucao);

		conexao.commit();
		conexao.close();
		scriptParaExecucao.close();
	}
}