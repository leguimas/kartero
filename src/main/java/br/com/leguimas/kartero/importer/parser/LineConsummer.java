package br.com.leguimas.kartero.importer.parser;

public class LineConsummer {
	private String[] line;

	public String[] getLine() {
		return line;
	}

	public void setLine(String[] line) {
		this.line = line;
	}

	public Integer asInteger(int i) {
		String string = accessLine(i);
		if (!isBlank(string)) {
			return Integer.parseInt(string);
		}

		return null;
	}

	public String asString(int i) {
		String string = accessLine(i);
		if (!isBlank(string)) {
			return string;
		}

		return null;
	}

	public Character asCharacter(int i) {
		String string = accessLine(i);
		if (!isBlank(string)) {
			return string.charAt(0);
		}

		return null;
	}

	private String accessLine(int i) {
		if (i < line.length) {
			return line[i];
		}
		return null;
	}

	private boolean isBlank(String s) {
		return (s == null || "".equals(s.trim()));
	}
}
