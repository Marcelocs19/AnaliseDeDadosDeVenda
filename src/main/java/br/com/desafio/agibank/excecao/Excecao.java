package br.com.desafio.agibank.excecao;

public class Excecao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public Excecao(String mensagem) {
		super(mensagem);
	}

	public Excecao(String msg, Throwable cause) {
		super(msg,cause);
	}

}
