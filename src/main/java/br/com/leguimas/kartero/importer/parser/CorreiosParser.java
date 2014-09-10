package br.com.leguimas.kartero.importer.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.leguimas.kartero.importer.entity.Bairro;
import br.com.leguimas.kartero.importer.entity.Localidade;

public class CorreiosParser {

    private static final String LOCALIDADE_FILE = "/LOG_LOCALIDADE.TXT";
    private static final String BAIRRO_FILE = "/LOG_BAIRRO.TXT";

    private List<Localidade> localidades;
    private List<Bairro> bairros;

    public List<Localidade> getLocalidades() {
        return localidades;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void parseFiles(String basePath) {
        localidades = parseLocalidades(basePath);
        bairros = parseBairros(basePath);
    }

    private List<Bairro> parseBairros(String basePath) {
        List<Bairro> ret = new ArrayList<Bairro>();
        List<String[]> file = readFile(basePath + BAIRRO_FILE);

        LineConsummer consummer = new LineConsummer();
        for (String[] line : file) {
            consummer.setLine(line);

            Bairro bairro = new Bairro();
            bairro.consumeLine(consummer);
            ret.add(bairro);
        }

        return ret;
    }

    private List<Localidade> parseLocalidades(String basePath) {

        List<Localidade> ret = new ArrayList<Localidade>();
        List<String[]> file = readFile(basePath + LOCALIDADE_FILE);

        LineConsummer consummer = new LineConsummer();
        for (String[] line : file) {
            consummer.setLine(line);

            Localidade localidade = new Localidade();
            localidade.consumeLine(consummer);
            ret.add(localidade);
        }

        return ret;
    }

    private List<String[]> readFile(String path) {
        List<String[]> ret = new ArrayList<String[]>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                ret.add(line.split("@"));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

        return ret;
    }
}
