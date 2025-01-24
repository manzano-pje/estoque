package com.api.estoque.dtos;

import com.api.estoque.entidades.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {


    @NotBlank(message = "O campo usuário não pode ser vazio.")
    @Column(unique = true)
    private String usuario;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 8, max = 15, message = "A senha deve ter entre 8 e 15 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Senha inválida. Caracteres aceitos: [A - Z] ou [0 - 9]")
    private String senha;

    public UsuarioDto(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
    }
}
