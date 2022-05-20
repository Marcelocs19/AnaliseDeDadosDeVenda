package br.com.desafio.agibank;

import java.util.Date;
import java.util.List;
import java.util.Timer;

import br.com.desafio.agibank.arquivo.gravacao.GravarTxt;
import br.com.desafio.agibank.arquivo.leitura.LeituraTxt;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelo.Relatorio;
import br.com.desafio.agibank.servico.AnaliseDadosServico;

public class App {

	public static void main(String[] args) {
		var timer = new Timer();
		
		var analiseDados = new AnaliseDadosServico();
		
		timer.schedule(analiseDados, new Date(), 600);
		
	}
}
