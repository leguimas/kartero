package br.com.leguimas.kartero.importer.entity;

import br.com.leguimas.kartero.importer.parser.LineConsummer;

public class Logradouro implements LineConsummerEntity {

	private Integer logNu;
	private String ufeSg;
	private Integer locNu;
	private Integer baiNuIni;
	private Integer baiNuFim;
	private String logNo;
	private String logComplemento;
	private String cep;
	private String tloTx;
	private Character logStaTlo;
	private String logNoAbrev;

	public Integer getLogNu() {
		return logNu;
	}

	public void setLogNu(Integer logNu) {
		this.logNu = logNu;
	}

	public String getUfeSg() {
		return ufeSg;
	}

	public void setUfeSg(String ufeSg) {
		this.ufeSg = ufeSg;
	}

	public Integer getLocNu() {
		return locNu;
	}

	public void setLocNu(Integer locNu) {
		this.locNu = locNu;
	}

	public Integer getBaiNuIni() {
		return baiNuIni;
	}

	public void setBaiNuIni(Integer baiNuIni) {
		this.baiNuIni = baiNuIni;
	}

	public Integer getBaiNuFim() {
		return baiNuFim;
	}

	public void setBaiNuFim(Integer baiNuFim) {
		this.baiNuFim = baiNuFim;
	}

	public String getLogNo() {
		return logNo;
	}

	public void setLogNo(String logNo) {
		this.logNo = logNo;
	}

	public String getLogComplemento() {
		return logComplemento;
	}

	public void setLogComplemento(String logComplemento) {
		this.logComplemento = logComplemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTloTx() {
		return tloTx;
	}

	public void setTloTx(String tloTx) {
		this.tloTx = tloTx;
	}

	public Character getLogStaTlo() {
		return logStaTlo;
	}

	public void setLogStaTlo(Character logStaTlo) {
		this.logStaTlo = logStaTlo;
	}

	public String getLogNoAbrev() {
		return logNoAbrev;
	}

	public void setLogNoAbrev(String logNoAbrev) {
		this.logNoAbrev = logNoAbrev;
	}

	@Override
	public void consumeLine(LineConsummer consummer) {
		setLogNu(consummer.asInteger(0));
		setUfeSg(consummer.asString(1));
		setLocNu(consummer.asInteger(2));
		setBaiNuIni(consummer.asInteger(3));
		setBaiNuFim(consummer.asInteger(4));
		setLogNo(consummer.asString(5));
		setLogComplemento(consummer.asString(6));
		setCep(consummer.asString(7));
		setTloTx(consummer.asString(8));
		setLogStaTlo(consummer.asCharacter(9));
		setLogNoAbrev(consummer.asString(10));
	}

}
