package br.com.desafio.agibank;

import java.util.Date;
import java.util.Timer;

import br.com.desafio.agibank.servico.AnaliseDadosServico;

public class App {

	public static void main(String[] args) {
		var timer = new Timer();
		
		var analiseDados = new AnaliseDadosServico();
		
		timer.schedule(analiseDados, new Date(), 600);
		
	}
}
