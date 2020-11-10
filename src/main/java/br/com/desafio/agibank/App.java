package br.com.desafio.agibank;

import java.io.IOException;

import br.com.desafio.agibank.leitura.LeituraTxt;
import br.com.desafio.agibank.modelos.Relatorio;

public class App {

	public static void main(String[] args) {
		var leitura = new LeituraTxt();
		
		try {
			
			Relatorio leituraArquivo = leitura.leituraArquivo();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
