package com.api.estoque.dtos;

import com.api.estoque.entidades.Produto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlteracaoProdutoDto {

    @NonNull
    @Column(unique = true)
    private String produto;
    @NonNull
    private int qtdMinima;
    @NonNull
    private int estoque;
    @NonNull
    private double valorCusto;

    public AlteracaoProdutoDto(Produto produto) {
        this.produto = produto.getProduto();
        this.qtdMinima = produto.getQtdMinima();
        this.estoque = produto.getEstoque();
        this.valorCusto = produto.getValorCusto();
    }
}

