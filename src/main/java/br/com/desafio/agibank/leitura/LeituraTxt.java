package br.com.desafio.agibank.leitura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
				
				if(identificador.equals("001")) {
					String[] split = line.split("ç");
					
					Vendedor vendedor = new Vendedor();
					vendedor.setCpf(split[1]);
					vendedor.setNome(split[2]);
					vendedor.setSalario(Double.valueOf(split[3]));
					
					listaVendedor.add(vendedor);

				}				
				
				if(identificador.equals("002")) {
					String[] split = line.split("ç");
					
					Cliente cliente = new Cliente();					
					cliente.setCnpj(split[1]);
					cliente.setNome(split[2]);
					cliente.setBusinessArea(split[3]);
					
					listaCliente.add(cliente);
				}
				
				if(identificador.equals("003")) {
					String[] split = line.split("ç");
					
					Venda venda = new Venda();
					venda.setId(Integer.valueOf(split[1]));
					venda.setNome(split[3]);
					
					List<Item> listaItens = new ArrayList<>();
					
					String[] itens = split[2].replace("[", "").replace("]", "").split(",");
					
					for (var s : itens) {
						String[] split2 = s.split("-");
						
						Item item = new Item();
						item.setId(Integer.valueOf(split2[0]));
						item.setQuantidade(Integer.valueOf(split2[1]));
						item.setPreco(Double.valueOf(split2[2]));			
						
						listaItens.add(item);
					}
					
					venda.setItem(listaItens.stream().sorted((item1, item2) -> item1.getPreco().compareTo(item2.getPreco())).collect(Collectors.toList()));
					listaVenda.add(venda);
				}
			}
		}

		listaVendedor.forEach(v -> System.out.println(v.toString()));
		listaCliente.forEach(v -> System.out.println(v.toString()));		
		listaVenda.forEach(v -> System.out.println(v.toString()));

	}

}
