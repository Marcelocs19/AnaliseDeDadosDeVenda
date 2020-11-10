package br.com.desafio.agibank.leitura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.desafio.agibank.excecao.Erros;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelos.Cliente;
import br.com.desafio.agibank.modelos.Item;
import br.com.desafio.agibank.modelos.Venda;
import br.com.desafio.agibank.modelos.Vendedor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {

	private String path = "HOMEPATH/data/in/arquivoTeste.txt";

	private List<Vendedor> listaVendedor = new ArrayList<>();
	
	private List<Cliente> listaCliente = new ArrayList<>();

	private List<Venda> listaVenda = new ArrayList<>();
	
	public void leituraArquivo() throws IOException {
		File fileReader = new File(path);
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
		
		listaVendedor.stream().forEach(v -> System.out.println(v.toString()));
		listaCliente.stream().forEach(v ->  System.out.println(v.toString()));
		listaVenda.stream().forEach(v ->  System.out.println(v.toString()));

	}

	private Vendedor criaVendedor(String[] split) {
		validaCamposVendedor(split);

		Vendedor vendedor = new Vendedor();

		vendedor.setCpf(split[1]);
		vendedor.setNome(split[2]);
		vendedor.setSalario(Double.valueOf(split[3]));

		return vendedor;
	}

	private void criaCliente(String[] split) {
		validaCamposCliente(split);

		Cliente cliente = new Cliente();
		cliente.setCnpj(split[1]);
		cliente.setNome(split[2]);
		cliente.setBusinessArea(split[3]);

		listaCliente.add(cliente);
	}

	private void criaVenda(String[] split) {
		validaCamposVenda(split);

		Venda venda = new Venda();
		venda.setId(Integer.valueOf(split[1]));
		venda.setNome(split[3]);

		List<Item> listaItens = new ArrayList<>();

		String[] itens = split[2].replace("[", "").replace("]", "").split(",");

		validaCamposItens(split);

		for (var s : itens) {
			String[] split2 = s.split("-");

			Item item = new Item();
			item.setId(Integer.valueOf(split2[0]));
			item.setQuantidade(Integer.valueOf(split2[1]));
			item.setPreco(Double.valueOf(split2[2]));

			listaItens.add(item);
		}

		venda.setItem(listaItens.stream().sorted((item1, item2) -> item1.getPreco().compareTo(item2.getPreco()))
				.collect(Collectors.toList()));
		listaVenda.add(venda);
	}

	private void validaCamposVendedor(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao("Ocorreu um erro na leitura dos dados do Vendedor");
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

	private void validaCamposCliente(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao("Ocorreu um erro na leitura dos dados do Cliente");
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

	private void validaCamposVenda(String[] split) {
		if (split.length < 4 || split.length > 4) {
			throw new Excecao("Ocorreu um erro na leitura dos dados de Venda");
		}
		if (split[1].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_ID_VENDA.getDescricao());
		}
		if (split[3].isBlank()) {
			throw new Excecao(Erros.MSG_ERRO_NOME.getDescricao());
		}
	}

	private void validaCamposItens(String[] split) {
		if (split.length < 3 || split.length > 3) {
			throw new Excecao("Ocorreu um erro na leitura dos dados dos Itens");
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
