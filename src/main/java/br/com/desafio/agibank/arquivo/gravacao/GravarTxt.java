package br.com.desafio.agibank.arquivo.gravacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.desafio.agibank.modelo.Relatorio;
import br.com.desafio.agibank.variaveis.Caminho;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GravarTxt {

	public void gravarArquivoTxt(Relatorio leituraArquivo) throws IOException {
		var pastaArquivo = new File(Caminho.CAMINHO_OUT.getDescricao());

		if (!pastaArquivo.exists()) {
			pastaArquivo.createNewFile();
			var arquivo = new FileWriter(pastaArquivo);
			arquivo.write(formataSaidaTxt(leituraArquivo));
			arquivo.close();

		} else {
			var contador = 1;
			var arquivoNovo = new File(Caminho.CAMINHO_OUT.getDescricao() + contador + ".txt");
			while (arquivoNovo.exists()) {
				arquivoNovo = new File(Caminho.CAMINHO_OUT.getDescricao() + ++contador + ".txt");
			}
			var arquivo = new FileWriter(arquivoNovo);
			arquivo.write(formataSaidaTxt(leituraArquivo));
			arquivo.close();
		}
	}

	private String formataSaidaTxt(Relatorio leituraArquivo) {
		return "Quantidade de Clientes: " + leituraArquivo.getQuantidadeClientes() +
				"\n" + "Quantidade de Vendedores: " + leituraArquivo.getQuantidadeVendedores() +
				"\n" + "ID da venda mais cara: " + leituraArquivo.getIdVenda() +
				"\n" + "O pior vendedor: " + leituraArquivo.getPiorVendedor();
	}

}
