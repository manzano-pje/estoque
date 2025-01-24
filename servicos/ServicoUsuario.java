package com.api.estoque.servicos;

import com.api.estoque.dtos.UsuarioDto;
import com.api.estoque.entidades.Usuario;
import com.api.estoque.excessoes.ExcessaoUsuarioJaCadastrado;
import com.api.estoque.excessoes.ExcessaoUsuarioOuSenhaInvalidos;
import com.api.estoque.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class ServicoUsuario {

    private final RepositorioUsuario repositorioUsuario;
    private final ModelMapper mapper;

    public UsuarioDto criarUsuario(UsuarioDto usuarioDto){
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsuario(usuarioDto.getUsuario());
        if(usuarioOptional.isPresent()){
            throw new ExcessaoUsuarioJaCadastrado();
        }
        if(usuarioDto.getUsuario().isBlank() || usuarioDto.getSenha().length() < 8){
            throw new ExcessaoUsuarioOuSenhaInvalidos();
        }
        Usuario usuario = new Usuario();
        usuario.setUsuario(usuarioDto.getUsuario().toUpperCase());
        usuario.setSenha(usuarioDto.getSenha());
        repositorioUsuario.save(usuario);
        return mapper.map(usuario, UsuarioDto.class);
    }
}
