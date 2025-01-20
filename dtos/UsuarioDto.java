package com.api.estoque.dtos;

import com.api.estoque.entidades.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @NotBlank
    private int idUsuario;
    @NotBlank
    @Column(unique = true)
    private String usuario;
    @NotBlank
    private String senha;

    public UsuarioDto(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
    }
}
