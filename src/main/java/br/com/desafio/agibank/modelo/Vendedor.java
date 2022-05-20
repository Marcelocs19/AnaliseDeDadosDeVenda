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

	public void populaVendedor(String[] separador) {
		this.setCpf((separador[1] != null) ? separador[1] : null);
		this.setNome((separador[2] != null) ? separador[2] : null);
		this.setSalario(Double.valueOf((separador[3] != null) ? separador[3] : null));
	}
	
}
