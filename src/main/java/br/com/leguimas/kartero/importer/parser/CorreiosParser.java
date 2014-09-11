package br.com.leguimas.kartero.importer.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.leguimas.kartero.importer.entity.Bairro;
import br.com.leguimas.kartero.importer.entity.Localidade;
import br.com.leguimas.kartero.importer.entity.Logradouro;

public class CorreiosParser {

    private static final String LOCALIDADE_FILE = "/LOG_LOCALIDADE.TXT";
    private static final String BAIRRO_FILE = "/LOG_BAIRRO.TXT";
    private static final String LOGRADOURO_FILE = "/LOG_LOGRADOURO_XX.TXT";

    private static final String[] UNIDADES_FEDERATIVAS = new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT",
            "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };

    private List<Localidade> localidades;
    private List<Bairro> bairros;
    private List<Logradouro> logradouros;

    public List<Localidade> getLocalidades() {
        return localidades;
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void parseFiles(String basePath) {
        localidades = parseLocalidades(basePath);
        bairros = parseBairros(basePath);
        logradouros = parseLogradouros(basePath);
    }

    private List<Logradouro> parseLogradouros(String basePath) {
        List<Logradouro> ret = new ArrayList<Logradouro>();
        List<String[]> file;

        LineConsummer consummer = new LineConsummer();
        
        for (String uf : UNIDADES_FEDERATIVAS) {
            file = readFile(basePath + LOGRADOURO_FILE.replace("XX", uf));
            
            for (String[] line : file) {
                consummer.setLine(line);
                
                Logradouro logradouro = new Logradouro();
                logradouro.consumeLine(consummer);
                ret.add(logradouro);
            }
        }

        return ret;
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
