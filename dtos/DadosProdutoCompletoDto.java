package com.api.estoque.dtos;

import com.api.estoque.entidades.Produto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosProdutoCompletoDto {

    @NonNull
    @Column(unique = true)
    private String codProduto;
    @NonNull
    @Column(unique = true)
    private String produto;
    @NonNull
    private int qtdMinima;
    @NonNull
    private int estoque;
    private Date dataCadastro;

    @NonNull
    private double valorCusto;

    public DadosProdutoCompletoDto(Produto produto) {
        this.codProduto = produto.getCodProduto();
        this.produto = produto.getProduto();
        this.qtdMinima = produto.getQtdMinima();
        this.estoque = produto.getEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.valorCusto = produto.getValorCusto();
    }
}

