package com.api.estoque.dtos;

import com.api.estoque.entidades.Produto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {

    private int idProdutos;
    @NotBlank
    @Column(unique = true)
    private String codProduto;
    @NotBlank
    @Column(unique = true)
    private String produto;
    @NotBlank
    private int qtdMinima;
    @NotBlank
    private int estoque;
    @NotBlank
    private Date dataCadastro;
    @NotBlank
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
