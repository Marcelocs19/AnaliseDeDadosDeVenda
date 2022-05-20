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

public class Validacao {

    public static boolean validaCamposVendedor(Vendedor vendedor) {
        validaCPF(vendedor.getCpf());
        if (vendedor.getCpf() == null || vendedor.getCpf().isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
        }
        if (vendedor.getNome() == null || vendedor.getNome().isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
        }
        if (vendedor.getSalario() == null) {
            throw new Excecao(Erros.MSG_ERRO_SALARIO.getDescricao());
        }
        return true;
    }

    public static boolean validaCamposCliente(Cliente cliente) {
        validaCNPJ(cliente.getCnpj());
        if (cliente.getCnpj() == null || cliente.getCnpj().isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
        }
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
        }
        if (cliente.getBusinessArea() == null || cliente.getBusinessArea().isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_BUSINESS_AREA.getDescricao());
        }
        return true;
    }

    public static boolean validaCamposVenda(Venda venda) {
        if (venda.getId() == null) {
            throw new Excecao(Erros.MSG_ERRO_ID_VENDA.getDescricao());
        }
        if (venda.getNome() == null || venda.getNome().isBlank()) {
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
        if (cpf == null || cpf.isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
        }
        try {
            cpfValidator.assertValid(cpf);
        } catch (Exception e) {
            throw new Excecao(Erros.MSG_ERRO_CPF.getDescricao());
        }

    }

    private static void validaCNPJ(String cnpj) {
        CNPJValidator cnpjValidator = new CNPJValidator();
        if (cnpj == null || cnpj.isBlank()) {
            throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
        }
        try {
            cnpjValidator.assertValid(cnpj);
        } catch (Exception e) {
            throw new Excecao(Erros.MSG_ERRO_CNPJ.getDescricao());
        }
    }

}
