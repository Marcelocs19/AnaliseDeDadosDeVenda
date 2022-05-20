package br.com.desafio.agibank.servico;

import br.com.desafio.agibank.modelo.Relatorio;
import br.com.desafio.agibank.modelo.Venda;

import java.util.List;

public class RelatorioServico {

    private CriacaoDadosServico criacaoDadosServico = new CriacaoDadosServico();

    public Relatorio geraRelatorio(final int qtdVendedores, final int qtdClientes, List<Venda> listaVenda ) {
        var relatorio = new Relatorio();

        relatorio.setQuantidadeVendedores(qtdVendedores);
        relatorio.setQuantidadeClientes(qtdClientes);
        relatorio.setIdVenda(criacaoDadosServico.vendaMaisCara(listaVenda));
        relatorio.setPiorVendedor(criacaoDadosServico.piorVendedor(listaVenda));

        return relatorio;
    }
}
