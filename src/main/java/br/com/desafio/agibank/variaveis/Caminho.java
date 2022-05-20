package br.com.desafio.agibank.variaveis;

public enum Caminho {

    CAMINHO_IN("C:\\Users\\m_cam\\OneDrive\\Área de Trabalho\\Nova pasta\\data\\in\\"),
    CAMINHO_OUT("D:\\data\\out\\relatorio.txt"),
    ;

    private String descricao;


    Caminho(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
