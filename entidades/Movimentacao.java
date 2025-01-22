package com.api.estoque.entidades;


import com.api.estoque.entidades.enums.TipoMovimentacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimentacao;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao TipoMovimentacao;
    @NotBlank
    private Date dataMovimentacao;
    @NotBlank
    private double valor;

    // produto
    // usuario

    /******************
     * RELACIONAMENTOS
     ******************/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_produto")
    private Produto produto;
}
