package br.com.desafio.agibank.modelo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Item {

    private Integer id;

    private Integer quantidade;

    private Double preco;

    private Double valorTotal;

    public void populaItem(String[] separadorItens) {
        this.setId(Integer.valueOf(separadorItens[0]));
        this.setQuantidade(Integer.valueOf(separadorItens[1]));
        this.setPreco(Double.valueOf(separadorItens[2]));

        this.setValorTotal(this.getQuantidade() * this.getPreco());
    }

}
