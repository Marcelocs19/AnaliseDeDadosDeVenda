package br.com.desafio.agibank.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Cliente {

	private String cnpj;
	
	private String nome;
	
	private String businessArea;
	
}
