package com.api.estoque.dtos;

import com.api.estoque.entidades.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private int idProdutos;
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
    @NonNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataCadastro;
    @NonNull
    private double valorCusto;

    public ProdutoDto(Produto produto) {
        this.idProdutos = produto.getIdProdutos();
        this.codProduto = produto.getCodProduto();
        this.produto = produto.getProduto();
        this.qtdMinima = produto.getQtdMinima();
        this.estoque = produto.getEstoque();
        this.dataCadastro = produto.getDataCadastro();
        this.valorCusto = produto.getValorCusto();
    }
}
