package br.com.leguimas.kartero;

public class KarteroException extends RuntimeException {

	private static final long serialVersionUID = -4660251427242894057L;

	private String mensagem;

	private Exception excecao;

	public KarteroException(Exception excecao, String mensagem) {
		this.excecao = excecao;
		this.mensagem = mensagem;
	}

	public String obtemMensage() {
		return this.mensagem;
	}

	public Exception obtemExcecao() {
		return this.excecao;
	}

}
