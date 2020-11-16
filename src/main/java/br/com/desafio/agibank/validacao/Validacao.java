package br.com.desafio.agibank.validacao;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelo.Cliente;
import br.com.desafio.agibank.modelo.Item;
import br.com.desafio.agibank.modelo.Venda;
import br.com.desafio.agibank.modelo.Vendedor;
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

	public static boolean validaCamposVendedor(Vendedor vendedor) {
		validaCPF(vendedor.getCpf());
		if (vendedor.getCpf().isBlank()) {			
			throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
		}
		if (vendedor.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (vendedor.getSalario() == null) {
			throw new Excecao(Erros.MSG_ERRO_SALARIO.getDescricao());
		}
		return true;
	}

	public static boolean validaCamposCliente(Cliente cliente) {
		validaCNPJ(cliente.getCnpj());
		if (cliente.getCnpj().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
		}
		if (cliente.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		if (cliente.getBusinessArea().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_BUSINESS_AREA.getDescricao());
		}
		return true;
	}

	public static boolean validaCamposVenda(Venda venda) {
		if (venda.getId() == null) {
			throw new Excecao(Erros.MSG_ERRO_ID_VENDA.getDescricao());
		}
		if (venda.getNome().isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
		return true;
	}

	public static boolean validaCamposItem(Item item) {
		if (item.getId() == null) {
			throw new Excecao(Erros.MSG_ERRO_ID_ITEM.getDescricao());
		}
		if (item.getQuantidade() == null) {
			throw new Excecao(Erros.MSG_ERRO_QUANTIDADE.getDescricao());
		}
		if (item.getPreco() == null) {
			throw new Excecao(Erros.MSG_ERRO_PRECO.getDescricao());
		}
		return true;
	}

	private static void validaCPF(String cpf) {
		CPFValidator cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(cpf);			
		} catch (Exception e) {
			throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
		}

	}
	
	private static void validaCNPJ(String cnpj) {
		CNPJValidator cnpjValidator = new CNPJValidator();
		try {
			cnpjValidator.assertValid(cnpj);				
		} catch (Exception e) {
			throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
		}
	}

}
