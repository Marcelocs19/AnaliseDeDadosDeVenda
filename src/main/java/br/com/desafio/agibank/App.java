package br.com.desafio.agibank;

import java.util.Date;
import java.util.Timer;

import br.com.desafio.agibank.arquivo.leitura.LeituraTxt;

public class App {

	public static void main(String[] args) {
		int intervalo = 5; // 1 mim
		Timer timer = new Timer();
		
		var leitura = new LeituraTxt();
		
		timer.schedule(leitura, new Date(), 6000 * intervalo);
		
	}
}
