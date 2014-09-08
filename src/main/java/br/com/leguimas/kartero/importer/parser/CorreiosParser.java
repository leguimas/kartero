package br.com.leguimas.kartero.importer.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.leguimas.kartero.importer.entity.Localidade;

public class CorreiosParser {

    private static final String LOCALIDADE_FILE = "/LOG_LOCALIDADE.TXT";

    private List<Localidade> localidades;

    public List<Localidade> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidade> localidades) {
        this.localidades = localidades;
    }

    public void parseFiles(String basePath) {
        localidades = parseLocalidades(basePath);
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
