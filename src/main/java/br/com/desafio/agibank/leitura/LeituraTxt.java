package br.com.desafio.agibank.leitura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.desafio.agibank.modelos.Cliente;
import br.com.desafio.agibank.modelos.Item;
import br.com.desafio.agibank.modelos.Venda;
import br.com.desafio.agibank.modelos.Vendedor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LeituraTxt {

	public void leituraArquivo() throws IOException {
		String path = "HOMEPATH/data/in/arquivoTeste.txt";

		File fileReader = new File(path);
		FileReader fr = new FileReader(fileReader);
		List<Vendedor> listaVendedor = new ArrayList<>();
		List<Cliente> listaCliente = new ArrayList<>();
		List<Venda> listaVenda = new ArrayList<>();
		
		try (BufferedReader bufferedReader = new BufferedReader(fr)) {
			String line = "";
			while (bufferedReader.ready()) {
				line = bufferedReader.readLine();
				String identificador = line.substring(0, 3);
				if(identificador.equals("001")) {
					Vendedor vendedor = new Vendedor();
					String[] split = line.split("ç");
					vendedor.setCpf(split[1]);
					vendedor.setNome(split[2]);
					vendedor.setSalario(Double.valueOf(split[3]));
					listaVendedor.add(vendedor);

				}				
				
				if(identificador.equals("002")) {
					Cliente cliente = new Cliente();
					String[] split = line.split("ç");
					cliente.setCnpj(split[1]);
					cliente.setNome(split[2]);
					cliente.setBusinessArea(split[3]);
					listaCliente.add(cliente);
				}
				
				if(identificador.equals("003")) {
					String[] split = line.split("ç");
					Venda venda = new Venda();
					List<Item> listaItens = new ArrayList<>();
					venda.setId(Integer.valueOf(split[1]));
					venda.setNome(split[3]);
					String[] itens = split[2].replace("[", "").replace("]", "").split(",");
					for (String s : itens) {
						String[] split2 = s.split("-");
						Item item = new Item();
						item.setId(Integer.valueOf(split2[0]));
						item.setQuantidade(Integer.valueOf(split2[1]));
						item.setPreco(Double.valueOf(split2[2]));						
						listaItens.add(item);
					}
					venda.setItem(listaItens);
					listaVenda.add(venda);
				}
			}
		}

		listaVendedor.forEach(v -> System.out.println(v.toString()));
		listaCliente.forEach(v -> System.out.println(v.toString()));
		listaVenda.forEach(v -> System.out.println(v.toString()));

	}

}
