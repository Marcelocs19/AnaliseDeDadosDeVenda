package br.com.desafio.agibank.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Relatorio {
	
	private Integer quantidadeClientes;
	
	private Integer quantidadeVendedores;
	
	private Integer idVenda;
	
	private String piorVendedor;

}
