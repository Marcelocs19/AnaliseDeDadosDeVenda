package br.com.desafio.agibank.validacao;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelos.Cliente;
import br.com.desafio.agibank.modelos.Item;
import br.com.desafio.agibank.modelos.Venda;
import br.com.desafio.agibank.modelos.Vendedor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validacao {

	public static void validaVendedor(String[] split) {
		if (split.length != 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_VENDEDOR.getDescricao());
		}		
	}
	
	public static void validaCliente(String[] split) {
		if (split.length != 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_CLIENTE.getDescricao());
		}	
	}
	
	public static void validaVenda(String[] split) {
		if (split.length != 4) {
			throw new Excecao(Erros.MSG_ARQUIVO_VENDA.getDescricao());
		}	
	}
	
	public static void validaItem(String[] split) {
		if (split.length != 3) {
			throw new Excecao(Erros.MSG_ARQUIVO_ITEM.getDescricao());
		}	
	}
	
	
	public static void validaCamposVendedor(Vendedor vendedor) {
		if (vendedor.getCpf().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
		}
		if (vendedor.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (vendedor.getSalario() == null) {
			throw new Excecao(Erros.MSG_ERRO_SALARIO.getDescricao());
		}
	}

	public static void validaCamposCliente(Cliente cliente) {
		if (cliente.getCnpj().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
		}
		if (cliente.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (cliente.getBusinessArea().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_BUSINESS_AREA.getDescricao());
		}
	}

	public static void validaCamposVenda(Venda venda) {		
		if (venda.getId() == null) {
			throw new Excecao(Erros.MSG_ERRO_ID_VENDA.getDescricao());
		}
		if (venda.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
	}

	public static void validaCamposItens(Item item) {
		if (item.getId() == null) {
			throw new Excecao(Erros.MSG_ERRO_ID_ITEM.getDescricao());
		}
		if (item.getQuantidade() == null) {
			throw new Excecao(Erros.MSG_ERRO_QUANTIDADE.getDescricao());
		}
		if (item.getPreco() == null) {
			throw new Excecao(Erros.MSG_ERRO_PRECO.getDescricao());
		}
	}
	
}
