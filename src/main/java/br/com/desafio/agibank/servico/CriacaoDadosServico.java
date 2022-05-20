package br.com.desafio.agibank.servico;

import br.com.desafio.agibank.modelo.Cliente;
import br.com.desafio.agibank.modelo.Item;
import br.com.desafio.agibank.modelo.Venda;
import br.com.desafio.agibank.modelo.Vendedor;
import br.com.desafio.agibank.validacao.Validacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CriacaoDadosServico {

    public Integer vendaMaisCara(List<Venda> vendas) {
        var maisCara = vendas.stream()
                .sorted(Comparator.comparing(Venda::getVendaTotal))
                .collect(Collectors.toList());
        Collections.reverse(maisCara);

        return maisCara.get(0).getId();
    }

    public String piorVendedor(List<Venda> vendas) {
        return vendas.stream().sorted(Comparator.comparing(Venda::getVendaTotal))
                .collect(Collectors.toList()).get(0).getNome();
    }

    public List<Vendedor> criaVendedor(String[] separador, List<Vendedor> listaVendedor) {
        var vendedor = new Vendedor();

        try {
            vendedor.setCpf((separador[1] != null) ? separador[1] : null);
            vendedor.setNome((separador[2] != null) ? separador[2] : null);
            vendedor.setSalario(Double.valueOf((separador[3] != null) ? separador[3] : null));
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Validacao.validaCamposVendedor(vendedor);

        listaVendedor.add(vendedor);

        return listaVendedor;
    }

    public List<Cliente> criaCliente(String[] separador, List<Cliente> listaCliente) {
        var cliente = new Cliente();

        try {
            cliente.setCnpj((separador[1] != null) ? separador[1] : null);
            cliente.setNome((separador[2] != null) ? separador[2] : null);
            cliente.setBusinessArea((separador[3] != null) ? separador[3] : null);

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Validacao.validaCamposCliente(cliente);

        listaCliente.add(cliente);

        return listaCliente;
    }

    public List<Venda> criaVenda(String[] separador, List<Venda> listaVenda) {
        var listaItensTotais = new ArrayList<Item>();

        var venda = new Venda();
        double valorTotalVendas = 0;

        try {
            venda.setId(Integer.valueOf((separador[1] != null) ? separador[1] : null));
            venda.setNome((separador[3] != null) ? separador[3] : null);

        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        Validacao.validaCamposVenda(venda);

        var listaItens = new ArrayList<Item>();

        var itens = separador[2].replace("[", "").replace("]", "").split(",");

        for (var s : itens) {
            var separadorItens = s.split("-");

            var item = new Item();
            item.setId(Integer.valueOf(separadorItens[0]));
            item.setQuantidade(Integer.valueOf(separadorItens[1]));
            item.setPreco(Double.valueOf(separadorItens[2]));

            item.setValorTotal(item.getQuantidade() * item.getPreco());

            valorTotalVendas += item.getValorTotal();

            Validacao.validaCamposItem(item);

            listaItens.add(item);
        }

        listaItensTotais.addAll(listaItens);

        venda.setVendaTotal(valorTotalVendas);
        venda.setItem(listaItens);

        listaVenda.add(venda);

        return listaVenda;
    }

}
