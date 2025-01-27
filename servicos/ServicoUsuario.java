package com.api.estoque.servicos;

import com.api.estoque.dtos.UsuarioDto;
import com.api.estoque.entidades.Usuario;
import com.api.estoque.excessoes.ExcessaoNaoExisteUsuarios;
import com.api.estoque.excessoes.ExcessaoUsuarioJaCadastrado;
import com.api.estoque.excessoes.ExcessaoUsuarioNaoCadastrado;
import com.api.estoque.excessoes.ExcessaoUsuarioOuSenhaInvalidos;
import com.api.estoque.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public void alterarSenha(String usuarioPesquisa, String senha){
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsuario(usuarioPesquisa);
        if(usuarioOptional.isEmpty()){
            throw new ExcessaoUsuarioNaoCadastrado();
        }
        if(usuarioPesquisa.isBlank() || senha.length() < 8 || senha.length() > 15 ){
            throw new ExcessaoUsuarioOuSenhaInvalidos();
        }
        Usuario usuario = usuarioOptional.get();
        usuario.setIdUsuario(usuario.getIdUsuario());
        usuario.setUsuario(usuarioOptional.get().getUsuario());
        usuario.setSenha(senha);
        repositorioUsuario.save(usuario);
    }

    public List<String> listarTodosUsuarios(){
        List<Usuario> listaUsuario = repositorioUsuario.findAll();
        if(listaUsuario.isEmpty()){
            throw new ExcessaoNaoExisteUsuarios();
        }
        return listaUsuario.stream()
                .map(Usuario::getUsuario)
                .collect(Collectors.toList());
    }

    public void apagaqrUsuario(String usuario){
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsuario(usuario);
        if(usuarioOptional.isEmpty()){
            throw new ExcessaoUsuarioNaoCadastrado();
        }
        if(usuario.isBlank()){
            throw new ExcessaoUsuarioOuSenhaInvalidos();
        }
        repositorioUsuario.deleteById(usuarioOptional.get().getIdUsuario());
    }
}
