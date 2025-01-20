package com.api.estoque.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    /******************
     * RELACIONAMENTOS
     ******************/
    
    @OneToMany(mappedBy = "produto")
    public List<Movimentacao> listaMovimentacao;
}
