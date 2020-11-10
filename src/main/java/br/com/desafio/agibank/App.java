package br.com.desafio.agibank;

import java.io.IOException;

import br.com.desafio.agibank.gravacao.GravarTxt;
import br.com.desafio.agibank.leitura.LeituraTxt;
import br.com.desafio.agibank.modelos.Relatorio;

public class App {

	public static void main(String[] args) {
		var leitura = new LeituraTxt();
		var gravar = new GravarTxt();
		try {
			
			Relatorio leituraArquivo = leitura.leituraArquivo();
			gravar.gravarArquivoTxt(leituraArquivo);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
