package br.com.leguimas.kartero;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.leguimas.kartero.api.CepRS;

public class KarteroApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CepRS.class);
		return classes;
	}

}
