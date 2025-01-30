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
public class ProdutoDto {

    @NonNull
    @Column(unique = true)
    private String produto;
    @NonNull
    private int qtdMinima;
    @NonNull
    private int estoque;
    @NonNull
    private double valorCusto;

    public ProdutoDto(Produto produto) {
        this.produto = produto.getProduto();
        this.qtdMinima = produto.getQtdMinima();
        this.estoque = produto.getEstoque();
        this.valorCusto = produto.getValorCusto();
    }
}

