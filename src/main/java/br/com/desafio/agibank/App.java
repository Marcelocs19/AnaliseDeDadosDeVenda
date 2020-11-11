package br.com.desafio.agibank;

import java.io.IOException;
import java.util.List;

import br.com.desafio.agibank.gravacao.GravarTxt;
import br.com.desafio.agibank.leitura.LeituraTxt;
import br.com.desafio.agibank.modelos.Relatorio;

public class App {

	public static void main(String[] args) {
		var leitura = new LeituraTxt();
		var gravar = new GravarTxt();
		
		List<String> pegaArquivoTxt = leitura.pegaArquivoTxt();
		
		try {			
			for (String arq : pegaArquivoTxt) {
				Relatorio leituraArquivo = leitura.leituraArquivo(arq);
				gravar.gravarArquivoTxt(leituraArquivo);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
