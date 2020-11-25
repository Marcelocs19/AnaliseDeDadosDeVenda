package br.com.desafio.agibank.arquivo.leitura;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelo.Cliente;
import br.com.desafio.agibank.modelo.Item;
import br.com.desafio.agibank.modelo.Relatorio;
import br.com.desafio.agibank.modelo.Venda;
import br.com.desafio.agibank.modelo.Vendedor;
import br.com.desafio.agibank.validacao.Validacao;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {

	private List<Vendedor> listaVendedor = new ArrayList<>();

	private List<Cliente> listaCliente = new ArrayList<>();

	private List<Venda> listaVenda = new ArrayList<>();

	private List<Item> listaItensTotais = new ArrayList<>();

	private List<String> listaArquivosLidos = new ArrayList<>();

	private static String CAMINHO = "C:\\data\\in\\";

	public List<String> pegaArquivoTxt() {
		File pastaArquivo = new File(CAMINHO);
		var listaNomes = pastaArquivo.list();
		List<String> nomesArquivos = new ArrayList<>();
		for (String nome : listaNomes) {
			if (nome.contains(".txt") && !listaArquivosLidos.contains(nome)) {
				nomesArquivos.add(nome);
				listaArquivosLidos.add(nome);
			}
		}

		return nomesArquivos;
	}

	public Relatorio leituraArquivo(String nomeArquivo) throws IOException {
        Path path = Paths.get(CAMINHO + nomeArquivo);

        Files.lines(path).forEach(linha -> {
        	var identificador = linha.substring(0, 3);

			if (identificador.equals("001")) {
				var separador = linha.split("ç");
				criaVendedor(separador);
			}

			else if (identificador.equals("002")) {
				var separador = linha.split("ç");
				criaCliente(separador);
			}

			else if (identificador.equals("003")) {
				var separador = linha.split("ç");
				criaVenda(separador);
			}

			else {
				throw new Excecao(Erros.MSG_ERRO_LEITURA_ARQUIVO.getDescricao());
			}
        });

		return criaRelatorio();
	}
	

	private Integer vendaMaisCara(List<Venda> vendas) {
		var maisCara = vendas.stream()
				.sorted((venda1, venda2) -> venda1.getVendaTotal().compareTo(venda2.getVendaTotal()))
				.collect(Collectors.toList());
		Collections.reverse(maisCara);

		return maisCara.get(0).getId();
	}

	private String piorVendedor(List<Venda> vendas) {
		var pior = vendas.stream().sorted((venda1, venda2) -> venda1.getVendaTotal().compareTo(venda2.getVendaTotal()))
				.collect(Collectors.toList());

		return pior.get(0).getNome();
	}

	private void criaVendedor(String[] separador) {
		var vendedor = new Vendedor();

		try {
			vendedor.setCpf((separador[1] != null) ? separador[1] : null);
			vendedor.setNome((separador[2] != null) ? separador[2] : null);
			vendedor.setSalario(Double.valueOf((separador[3] != null) ? separador[3] : null));
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposVendedor(vendedor);

		listaVendedor.add(vendedor);
	}

	private void criaCliente(String[] separador) {
		var cliente = new Cliente();

		try {
			cliente.setCnpj((separador[1] != null) ? separador[1] : null);
			cliente.setNome((separador[2] != null) ? separador[2] : null);
			cliente.setBusinessArea((separador[3] != null) ? separador[3] : null);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposCliente(cliente);

		listaCliente.add(cliente);
	}

	private void criaVenda(String[] separador) {
		var venda = new Venda();
		
		double valorTotalVendas = 0;
		
		try {
			venda.setId(Integer.valueOf((separador[1] != null) ? separador[1] : null));
			venda.setNome((separador[3] != null) ? separador[3] : null);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposVenda(venda);

		List<Item> listaItens = new ArrayList<>();

		var itens = separador[2].replace("[", "").replace("]", "").split(",");

		for (var s : itens) {
			var separadorItens = s.split("-");

			Item item = new Item();
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
	}
	
	private Relatorio criaRelatorio() {
		Relatorio relatorio = new Relatorio();
		
		relatorio.setQuantidadeVendedores(listaVendedor.size());
		relatorio.setQuantidadeClientes(listaCliente.size());
		relatorio.setIdVenda(vendaMaisCara(listaVenda));
		relatorio.setPiorVendedor(piorVendedor(listaVenda));

		return relatorio;
	}

}
