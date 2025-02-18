package com.api.estoque.controles;


import com.api.estoque.dtos.UsuarioDto;
import com.api.estoque.servicos.ServicoUsuario;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/usuario")
public class ControleUsuario {

    private final ServicoUsuario servicoUsuario;

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@Valid @RequestBody UsuarioDto usuarioDto){
        servicoUsuario.criarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso!");
    }

    @PatchMapping("/senha/{usuario}")
    public ResponseEntity<Object> alterarSenha(@Valid @PathVariable String usuario, @RequestBody String senha){
        servicoUsuario.alterarSenha(usuario, senha);
        return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso!");
    }

    @PatchMapping("/usuario/{usuario}")
    public ResponseEntity<Object> alterarUsuario(@Valid @PathVariable String usuario, @RequestBody String usuarioNovo){
        servicoUsuario.alterarUsuario(usuario, usuarioNovo);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário alterada com sucesso!");
    }

    @GetMapping
    public List<String> listarTodosUsuarios(){
        return servicoUsuario.listarTodosUsuarios();
    }

    @DeleteMapping("/{usuario}")
    public ResponseEntity<Object> apagarUsuario(@PathVariable String usuario){
        servicoUsuario.apagarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
    }
}
