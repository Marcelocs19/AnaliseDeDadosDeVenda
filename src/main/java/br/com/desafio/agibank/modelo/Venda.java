package br.com.desafio.agibank.modelo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Venda {

	private Integer id;
	
	private List<Item> item;
	
	private String nome;
	
	private Double vendaTotal;

	public void populaVenda(String[] separador) {
		this.setId(Integer.valueOf((separador[1] != null) ? separador[1] : null));
		this.setNome((separador[3] != null) ? separador[3] : null);
	}

}
