package br.com.desafio.agibank.arquivo.leitura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
			
	public List<String> pegaArquivoTxt() {
		File fileReader = new File(System.getProperty("user.home"), "Desktop/HOMEPATH/data/in/");		
		String[] list = fileReader.list();
		List<String> nomesArquivos = new ArrayList<>();
		for(int i = 0; i < list.length; i++) {
			if(list[i].contains(".txt")) {
				nomesArquivos.add(list[i]);
			}
		}
		return nomesArquivos;
	}

	public Relatorio leituraArquivo(String nomeArquivo) throws IOException {
		Relatorio relatorio = new Relatorio();
		File fileReader = new File(System.getProperty("user.home"), "Desktop/HOMEPATH/data/in/" + nomeArquivo);	
		FileReader fr = new FileReader(fileReader);

		try (BufferedReader bufferedReader = new BufferedReader(fr)) {
			var line = "";
			while (bufferedReader.ready()) {
				line = bufferedReader.readLine();
				var identificador = line.substring(0, 3);

				if (identificador.equals("001")) {
					String[] split = line.split("ç");
					listaVendedor.add(criaVendedor(split));
				}

				else if (identificador.equals("002")) {
					String[] split = line.split("ç");
					criaCliente(split);
				}

				else if (identificador.equals("003")) {
					String[] split = line.split("ç");
					criaVenda(split);
				}

				else {
					throw new Excecao("Ocorreu um erro na leitura do arquivo");
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
		var pior = vendas.stream()
				.sorted((venda1, venda2) -> venda1.getVendaTotal().compareTo(venda2.getVendaTotal()))
				.collect(Collectors.toList());
				
		return pior.get(0).getNome();
	}
	

	private Vendedor criaVendedor(String[] split) {
		Validacao.validaCamposVendedor(split);

		Vendedor vendedor = new Vendedor();

		vendedor.setCpf(split[1]);
		vendedor.setNome(split[2]);
		vendedor.setSalario(Double.valueOf(split[3]));

		return vendedor;
	}

	private void criaCliente(String[] split) {
		Validacao.validaCamposCliente(split);

		Cliente cliente = new Cliente();
		cliente.setCnpj(split[1]);
		cliente.setNome(split[2]);
		cliente.setBusinessArea(split[3]);

		listaCliente.add(cliente);
	}

	private void criaVenda(String[] split) {
		Validacao.validaCamposVenda(split);
		double valorTotalVendas = 0;

		var venda = new Venda();
		venda.setId(Integer.valueOf(split[1]));
		venda.setNome(split[3]);

		List<Item> listaItens = new ArrayList<>();

		String[] itens = split[2].replace("[", "").replace("]", "").split(",");

		Validacao.validaCamposItens(itens);

		for (var s : itens) {
			String[] split2 = s.split("-");

			Item item = new Item();
			item.setId(Integer.valueOf(split2[0]));
			item.setQuantidade(Integer.valueOf(split2[1]));
			item.setPreco(Double.valueOf(split2[2]));

			item.setValorTotal(item.getQuantidade() * item.getPreco());

			valorTotalVendas += item.getValorTotal();
			
			listaItens.add(item);			
		}
		listaItensTotais.addAll(listaItens);
		venda.setVendaTotal(valorTotalVendas);
		venda.setItem(listaItens);
		listaVenda.add(venda);
	}

	

}