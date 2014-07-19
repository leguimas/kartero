package br.com.leguimas.kartero.cep;

import javax.json.Json;
import javax.json.JsonObject;

public class CEP {

	private String cep;

	private String logradouro = new String();

	private String bairro = new String();

	private String cidade = new String();

	private String uf = new String();

	public CEP(String cep) {
		this.cep = cep.replaceAll("[^0-9]", "");
	}

	public String obtemCEP() {
		return this.cep;
	}

	public void comLogradouro(String tipoLogradouro, String logradouro) {
		StringBuilder logradouroCompleto = new StringBuilder(tipoLogradouro);
		logradouroCompleto.append(" ").append(logradouro);
		this.logradouro = logradouroCompleto.toString();
	}

	public String obtemLogradouro() {
		return this.logradouro;
	}

	public void comBairro(String bairro) {
		this.bairro = bairro;
	}

	public String obtemBairro() {
		return this.bairro;
	}

	public void comCidade(String cidade) {
		this.cidade = cidade;
	}

	public String obtemCidade() {
		return this.cidade;
	}

	public void comUf(String uf) {
		this.uf = uf;
	}

	public String obtemUf() {
		return this.uf;
	}

	public JsonObject emFormatoJson() {
		JsonObject cepEmJson = Json.createObjectBuilder().add("cep", this.cep).add("logradouro", this.logradouro)
				.add("bairro", this.bairro).add("cidade", this.cidade).add("uf", this.uf).build();
		return cepEmJson;
	}

}
