package br.com.leguimas.kartero;

import java.io.IOException;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.ServerAcl.AclFormatException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.webapp.WebAppContext;

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

}