package br.com.leguimas.kartero;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public abstract class TesteIntegrado {

	private static Server servidor;

	@BeforeClass
	public static void iniciaServidor() throws Exception {
		servidor = new Server(8080);
		servidor.setStopAtShutdown(true);

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setResourceBase("src/main/webapp");
		webAppContext.setClassLoader(TesteIntegrado.class.getClassLoader());

		servidor.addHandler(webAppContext);
		servidor.start();
	}

	@AfterClass
	public static void finalizaServidor() throws Exception {
		servidor.stop();
	}

}