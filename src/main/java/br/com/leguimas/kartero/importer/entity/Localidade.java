package br.com.leguimas.kartero.importer.entity;

import br.com.leguimas.kartero.importer.parser.LineConsummer;

public class Localidade implements LineConsummerEntity {
    private Integer locNu;
    private String ufeSg;
    private String locNo;
    private String cep;
    private Character locInSit;
    private Character locInTipoLoc;
    private Integer locNuSub;
    private String locNoAbrev;
    private String munNu;

    public Integer getLocNu() {
        return locNu;
    }

    public void setLocNu(Integer locNu) {
        this.locNu = locNu;
    }

    public String getUfeSg() {
        return ufeSg;
    }

    public void setUfeSg(String ufeSg) {
        this.ufeSg = ufeSg;
    }

    public String getLocNo() {
        return locNo;
    }

    public void setLocNo(String locNo) {
        this.locNo = locNo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Character getLocInSit() {
        return locInSit;
    }

    public void setLocInSit(Character locInSit) {
        this.locInSit = locInSit;
    }

    public Character getLocInTipoLoc() {
        return locInTipoLoc;
    }

    public void setLocInTipoLoc(Character locInTipoLoc) {
        this.locInTipoLoc = locInTipoLoc;
    }

    public Integer getLocNuSub() {
        return locNuSub;
    }

    public void setLocNuSub(Integer locNuSub) {
        this.locNuSub = locNuSub;
    }

    public String getLocNoAbrev() {
        return locNoAbrev;
    }

    public void setLocNoAbrev(String locNoAbrev) {
        this.locNoAbrev = locNoAbrev;
    }

    public String getMunNu() {
        return munNu;
    }

    public void setMunNu(String munNu) {
        this.munNu = munNu;
    }

    @Override
    public void consumeLine(LineConsummer consummer) {
        setLocNu(consummer.asInteger(0));
        setUfeSg(consummer.asString(1));
        setLocNo(consummer.asString(2));
        setCep(consummer.asString(3));
        setLocInSit(consummer.asCharacter(4));
        setLocInTipoLoc(consummer.asCharacter(5));
        setLocNuSub(consummer.asInteger(6));
        setLocNoAbrev(consummer.asString(7));
        setMunNu(consummer.asString(8));
    }

}
