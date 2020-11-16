package br.com.desafio.agibank.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Vendedor {

	private String cpf;
	
	private String nome;
	
	private Double salario;
	
}
