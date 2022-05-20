package br.com.desafio.agibank.servico;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

import br.com.desafio.agibank.arquivo.gravacao.GravarTxt;
import br.com.desafio.agibank.arquivo.leitura.LeituraTxt;
import br.com.desafio.agibank.excecao.Excecao;
import br.com.desafio.agibank.modelo.Relatorio;

public class AnaliseDadosServico extends TimerTask {

    private LeituraTxt leituraTxt = new LeituraTxt();

    private GravarTxt gravarTxt = new GravarTxt();

    public void iniciaProjeto() {
        var pegaArquivoTxt = leituraTxt.pegaArquivoTxt();
        if (!pegaArquivoTxt.isEmpty()) {
            try {
                for (String arq : pegaArquivoTxt) {
                    Relatorio leituraArquivo = leituraTxt.leituraArquivo(arq);
                    gravarTxt.gravarArquivoTxt(leituraArquivo);
                    leituraTxt.limpaListas();
                }
            } catch (Excecao e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        iniciaProjeto();
    }
}
