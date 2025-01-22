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
    @Column(unique = true)
    private String codProduto;
    @Column(unique = true)
    private String produto;
    private int qtdMinima;
    private int estoque;
    private Date dataCadastro;
    private double valorCusto;

    /******************
     * RELACIONAMENTOS
     ******************/
    
    @OneToMany(mappedBy = "produto")
    public List<Movimentacao> listaMovimentacao;
}
