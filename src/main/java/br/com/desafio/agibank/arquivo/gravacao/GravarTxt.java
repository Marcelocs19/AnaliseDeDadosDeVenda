package br.com.desafio.agibank.arquivo.gravacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.desafio.agibank.modelos.Relatorio;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GravarTxt {

	private String path = "/HOMEPATH/data/out/relatorio.txt";

	public void gravarArquivoTxt(Relatorio leituraArquivo) throws IOException {
		File fileReader = new File(System.getProperty("user.home"), "Desktop" + path);

		if (!fileReader.exists()) {
			fileReader.createNewFile();
			FileWriter arquivo = new FileWriter(fileReader);
			arquivo.write(formataSaidaTxt(leituraArquivo));
			arquivo.close();

		} else {
			int count = 1;
			File file = new File(System.getProperty("user.home"), "Desktop/HOMEPATH/data/out/relatorio" + count + ".txt");
			while (file.exists()) {
				count++;
				file = new File(System.getProperty("user.home"), "Desktop/HOMEPATH/data/out/relatorio" + count + ".txt");
			}
			FileWriter arquivo = new FileWriter(file);
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
