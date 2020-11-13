package br.com.desafio.agibank.arquivo.leitura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelos.Cliente;
import br.com.desafio.agibank.modelos.Item;
import br.com.desafio.agibank.modelos.Relatorio;
import br.com.desafio.agibank.modelos.Venda;
import br.com.desafio.agibank.modelos.Vendedor;
import br.com.desafio.agibank.validacao.Validacao;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {

	private List<Vendedor> listaVendedor = new ArrayList<>();

	private List<Cliente> listaCliente = new ArrayList<>();

	private List<Venda> listaVenda = new ArrayList<>();

	private List<Item> listaItensTotais = new ArrayList<>();

	private List<String> listaArquivosLidos = new ArrayList<>();

	private String path = "Desktop/HOMEPATH/data/in/";

	public List<String> pegaArquivoTxt() {
		File fileReader = new File(System.getProperty("user.home"), path);
		var list = fileReader.list();
		List<String> nomesArquivos = new ArrayList<>();
		for (String nome : list) {
			if (nome.contains(".txt") && !listaArquivosLidos.contains(nome)) {
				nomesArquivos.add(nome);
				listaArquivosLidos.add(nome);
			}
		}

		return nomesArquivos;
	}

	public Relatorio leituraArquivo(String nomeArquivo) throws IOException {
		Relatorio relatorio = new Relatorio();
		File fileReader = new File(System.getProperty("user.home"), path + nomeArquivo);
		FileReader fr = new FileReader(fileReader);

		try (BufferedReader bufferedReader = new BufferedReader(fr)) {
			var line = "";
			
			while (bufferedReader.ready()) {
				line = bufferedReader.readLine();
				var identificador = line.substring(0, 3);

				if (identificador.equals("001")) {
					var split = line.split("ç");
					criaVendedor(split);
				}

				else if (identificador.equals("002")) {
					var split = line.split("ç");
					criaCliente(split);
				}

				else if (identificador.equals("003")) {
					var split = line.split("ç");
					criaVenda(split);
				}

				else {
					throw new Excecao(Erros.MSG_ERRO_LEITURA_ARQUIVO.getDescricao());
				}
			}
		}
		relatorio.setQuantidadeVendedores(listaVendedor.size());
		relatorio.setQuantidadeClientes(listaCliente.size());
		relatorio.setIdVenda(vendaMaisCara(listaVenda));
		relatorio.setPiorVendedor(piorVendedor(listaVenda));

		return relatorio;
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

	private void criaVendedor(String[] split) {
		var vendedor = new Vendedor();

		try {
			vendedor.setCpf((split[1] != null) ? split[1] : null);
			vendedor.setNome((split[2] != null) ? split[2] : null);
			vendedor.setSalario(Double.valueOf((split[3] != null) ? split[3] : null));
		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposVendedor(vendedor);

		listaVendedor.add(vendedor);
	}

	private void criaCliente(String[] split) {
		var cliente = new Cliente();

		try {
			cliente.setCnpj((split[1] != null) ? split[1] : null);
			cliente.setNome((split[2] != null) ? split[2] : null);
			cliente.setBusinessArea((split[3] != null) ? split[3] : null);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposCliente(cliente);

		listaCliente.add(cliente);
	}

	private void criaVenda(String[] split) {
		double valorTotalVendas = 0;

		var venda = new Venda();
		try {
			venda.setId(Integer.valueOf((split[1] != null) ? split[1] : null));
			venda.setNome((split[3] != null) ? split[3] : null);

		} catch (ArrayIndexOutOfBoundsException e) {
		}

		Validacao.validaCamposVenda(venda);

		List<Item> listaItens = new ArrayList<>();

		var itens = split[2].replace("[", "").replace("]", "").split(",");

		for (var s : itens) {
			var split2 = s.split("-");

			Item item = new Item();
			item.setId(Integer.valueOf(split2[0]));
			item.setQuantidade(Integer.valueOf(split2[1]));
			item.setPreco(Double.valueOf(split2[2]));

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

}
