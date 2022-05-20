package br.com.desafio.agibank.variaveis;

public enum Caminho {

    CAMINHO_ENTRADA("C:\\Users\\m_cam\\OneDrive\\Área de Trabalho\\Nova pasta\\data\\in\\"),
    CAMINHO_SAIDA("C:\\Users\\m_cam\\OneDrive\\Área de Trabalho\\Nova pasta\\data\\out\\");

    private String descricao;

    Caminho(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
