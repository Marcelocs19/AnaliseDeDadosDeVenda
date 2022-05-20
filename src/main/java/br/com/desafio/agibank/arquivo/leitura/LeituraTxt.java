package br.com.desafio.agibank.arquivo.leitura;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelo.Cliente;
import br.com.desafio.agibank.modelo.Item;
import br.com.desafio.agibank.modelo.Relatorio;
import br.com.desafio.agibank.modelo.Venda;
import br.com.desafio.agibank.modelo.Vendedor;
import br.com.desafio.agibank.servico.CriacaoDadosServico;
import br.com.desafio.agibank.servico.RelatorioServico;
import br.com.desafio.agibank.validacao.Validacao;
import br.com.desafio.agibank.variaveis.Caminho;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {

    private List<Vendedor> listaVendedor = new ArrayList<>();

    private List<Cliente> listaCliente = new ArrayList<>();

    private List<Venda> listaVenda = new ArrayList<>();

    private List<String> listaArquivosLidos = new ArrayList<>();

    private CriacaoDadosServico criacaoDadosServico = new CriacaoDadosServico();

    private RelatorioServico relatorioServico = new RelatorioServico();

    public List<String> pegaArquivoTxt() {
        var pastaArquivo = new File(Caminho.CAMINHO_ENTRADA.getDescricao());
        List<String> nomesArquivos = new ArrayList<>();
        for (var nome : pastaArquivo.list()) {
            if (nome.contains(".txt") && !listaArquivosLidos.contains(nome)) {
                nomesArquivos.add(nome);
                listaArquivosLidos.add(nome);
            }
        }
        return nomesArquivos;
    }

    public Relatorio leituraArquivo(String nomeArquivo) throws IOException {
        var path = Paths.get(Caminho.CAMINHO_ENTRADA.getDescricao() + nomeArquivo);

        Files.lines(path).forEach(l -> {
            var linha = l.split("ç");
            switch (linha[0]) {
                case "001":
                    listaVendedor = criacaoDadosServico.criaVendedor(linha, listaVendedor);
                    break;
                case "002":
                    listaCliente = criacaoDadosServico.criaCliente(linha, listaCliente);
                    break;
                case "003":
                    listaVenda = criacaoDadosServico.criaVenda(linha, listaVenda);
                    break;
                default:
                    throw new Excecao(Erros.MSG_ERRO_LEITURA_ARQUIVO.getDescricao());
            }
        });

        return relatorioServico.geraRelatorio(listaVendedor.size(), listaCliente.size(), listaVenda);
    }

    public void limpaListas() {
        listaVendedor = new ArrayList<>();
        listaCliente = new ArrayList<>();
        listaVenda = new ArrayList<>();
    }

}
