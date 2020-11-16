package br.com.desafio.agibank.arquivo.gravacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.desafio.agibank.modelo.Relatorio;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GravarTxt {
	
	private static String CAMINHO = "C:\\data\\out\\relatorio.txt";

	public void gravarArquivoTxt(Relatorio leituraArquivo) throws IOException {
		File pastaArquivo = new File(CAMINHO);

		if (!pastaArquivo.exists()) {
			pastaArquivo.createNewFile();
			FileWriter arquivo = new FileWriter(pastaArquivo);
			arquivo.write(formataSaidaTxt(leituraArquivo));
			arquivo.close();

		} else {
			int contador = 1;
			File arquivoNovo = new File("C:\\data\\out\\relatorio" + contador + ".txt");
			while (arquivoNovo.exists()) {
				arquivoNovo = new File("C:\\data\\out\\relatorio" + ++contador + ".txt");
			}
			FileWriter arquivo = new FileWriter(arquivoNovo);
			arquivo.write(formataSaidaTxt(leituraArquivo));
			arquivo.close();

		}

	}

	private String formataSaidaTxt(Relatorio leituraArquivo) {
		return "Quantidade de Clientes: " + leituraArquivo.getQuantidadeClientes() + "\n" + "Quantidade de Vendedores: "
				+ leituraArquivo.getQuantidadeVendedores() + "\n" + "ID da venda mais cara: " + leituraArquivo.getIdVenda() + "\n"
				+ "O pior vendedor: " + leituraArquivo.getPiorVendedor();
	}

}
