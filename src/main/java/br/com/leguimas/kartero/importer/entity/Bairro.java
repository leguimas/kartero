package br.com.leguimas.kartero.importer.entity;

import br.com.leguimas.kartero.importer.parser.LineConsummer;

public class Bairro implements LineConsummerEntity {

	private int baiNu;
	private String ufeSg;
	private int locNu;
	private String baiNo;
	private String baiNoAbrev;

	public int getBaiNu() {
		return baiNu;
	}

	public void setBaiNu(int baiNu) {
		this.baiNu = baiNu;
	}

	public String getUfeSg() {
		return ufeSg;
	}

	public void setUfeSg(String ufeSg) {
		this.ufeSg = ufeSg;
	}

	public int getLocNu() {
		return locNu;
	}

	public void setLocNu(int locNu) {
		this.locNu = locNu;
	}

	public String getBaiNo() {
		return baiNo;
	}

	public void setBaiNo(String baiNo) {
		this.baiNo = baiNo;
	}

	public String getBaiNoAbrev() {
		return baiNoAbrev;
	}

	public void setBaiNoAbrev(String baiNoAbrev) {
		this.baiNoAbrev = baiNoAbrev;
	}

	@Override
	public void consumeLine(LineConsummer consummer) {
		setBaiNu(consummer.asInteger(0));
		setUfeSg(consummer.asString(1));
		setLocNu(consummer.asInteger(2));
		setBaiNo(consummer.asString(3));
		setBaiNoAbrev(consummer.asString(4));
	}

}
