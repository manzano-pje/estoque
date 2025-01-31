package com.api.estoque.entidades;


import com.api.estoque.entidades.enums.TipoMovimentacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

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
    @NonNull
    private LocalDate dataMovimentacao;
    @NonNull
    private int quantidade;

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
