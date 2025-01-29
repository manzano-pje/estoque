package com.api.estoque.controles;

import com.api.estoque.dtos.MovimentacaoDto;
import com.api.estoque.servicos.ServicoMovimentacao;
import jakarta.annotation.security.DenyAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("api/v1/movimentacao")
public class ControleMovimentacao {

    private final ServicoMovimentacao servicoMovimentacao;

    @PostMapping
    public ResponseEntity<Object> criarMovimentacao(@Valid @RequestBody MovimentacaoDto movimentacaoDto){
        Object resposta = servicoMovimentacao.criarMovimentacao(movimentacaoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }
}
