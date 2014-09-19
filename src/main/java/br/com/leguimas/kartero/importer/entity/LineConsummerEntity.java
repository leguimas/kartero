package br.com.leguimas.kartero.importer.entity;

import br.com.leguimas.kartero.importer.parser.LineConsummer;

public interface LineConsummerEntity {
	public abstract void consumeLine(LineConsummer consummer);
}
