package br.com.desafio.agibank.excecao;

public enum Erros {
	
	MSG_ERRO_CPF("O campo cpf está incorreto"),
	MSG_ERRO_NOME("O campo nome está incorreto"),
	MSG_ERRO_SALARIO("O campo salário está incorreto"),
	MSG_ERRO_BUSINESS_AREA("O campo business area está incorreto"),
	MSG_ERRO_CNPJ("O campo cnpj está incorreto"),
	MSG_ERRO_ID_VENDA("O campo id venda está incorreto"),
	MSG_ERRO_ID_ITEM("O campo id item está incorreto"),
	MSG_ERRO_QUANTIDADE("O campo id item está incorreto"),
	MSG_ERRO_PRECO("O campo preço está incorreto");
	
	private final String descricao;
	
	private Erros(String valor) {
		descricao = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
