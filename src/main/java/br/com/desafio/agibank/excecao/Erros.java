package br.com.desafio.agibank.excecao;

public enum Erros {
	
	MSG_ERRO_LEITURA_ARQUIVO("Ocorreu um erro na leitura do arquivo"),
	MSG_ARQUIVO_VENDEDOR("Ocorreu um erro na leitura dos dados do Vendedor"),
	MSG_ARQUIVO_CLIENTE("Ocorreu um erro na leitura dos dados do Cliente"),
	MSG_ARQUIVO_VENDA("Ocorreu um erro na leitura dos dados de Venda"),
	MSG_ARQUIVO_ITEM("Ocorreu um erro na leitura dos dados dos Itens"),
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
