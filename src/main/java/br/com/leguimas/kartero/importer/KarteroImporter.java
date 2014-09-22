package br.com.leguimas.kartero.importer;

import br.com.leguimas.kartero.importer.parser.CorreiosParser;

public class KarteroImporter {
	public static void main(String[] args) {
		String path = args[0];
		System.out.println("Importing base from: " + path);

		CorreiosParser parser = new CorreiosParser();
		parser.parseFiles(path);
		parser.saveToBase();

		System.out.println("Process complete");
	}
}
