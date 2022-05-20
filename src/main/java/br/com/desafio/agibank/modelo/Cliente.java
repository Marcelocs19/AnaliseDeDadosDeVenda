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

	public void populaCliente(String[] separador) {
		this.setCnpj((separador[1] != null) ? separador[1] : null);
		this.setNome((separador[2] != null) ? separador[2] : null);
		this.setBusinessArea((separador[3] != null) ? separador[3] : null);
	}

}
