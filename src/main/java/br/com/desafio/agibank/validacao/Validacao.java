package br.com.desafio.agibank.validacao;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validacao {

	public static void validaCamposVendedor(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_VENDEDOR.getDescricao());
		}
		if (split[1].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
		}
		if (split[2].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (split[3].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_SALARIO.getDescricao());
		}
	}

	public static void validaCamposCliente(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_CLIENTE.getDescricao());
		}
		if (split[1].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
		}
		if (split[2].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (split[3].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_BUSINESS_AREA.getDescricao());
		}
	}

	public static void validaCamposVenda(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_VENDA.getDescricao());
		}
		if (split[1].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_ID_VENDA.getDescricao());
		}
		if (split[3].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
	}

	public static void validaCamposItens(String[] split) {
		if (split.length < 3 || split.length > 3) {
			throw new Excecao(Erros.MSG_ARQUIVO_ITEM.getDescricao());
		}
		if (split[0].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_ID_ITEM.getDescricao());
		}
		if (split[1].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_QUANTIDADE.getDescricao());
		}
		if (split[2].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_PRECO.getDescricao());
		}
	}
	
}
