package br.com.desafio.agibank;

import java.io.IOException;

import br.com.desafio.agibank.leitura.LeituraTxt;

public class App {

	public static void main(String[] args) {
		var leitura = new LeituraTxt();
		
		try {
			
			leitura.leituraArquivo();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
