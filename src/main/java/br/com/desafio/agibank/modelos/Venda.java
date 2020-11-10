package br.com.desafio.agibank.modelos;

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
	
}
