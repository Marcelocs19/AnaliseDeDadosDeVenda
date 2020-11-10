package br.com.desafio.agibank.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Item {
	
	private Integer id;
	
	private Integer quantidade;
	
	private Double preco;

}
