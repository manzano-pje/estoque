package com.api.estoque.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @NonNull
    @Column(unique = true)
    private String usuario;
    @NonNull
    private String senha;

    /******************
     * RELACIONAMENTOS
     ******************/

    @OneToMany(mappedBy = "usuario")
    public List<Movimentacao> listaMovimentacao;

}
