package com.api.estoque.controles;

import com.api.estoque.dtos.MovimentacaoDto;
import com.api.estoque.servicos.ServicoMovimentacao;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

    @GetMapping
    public List<MovimentacaoDto> listartodasMovimentacoes(){
        return servicoMovimentacao.listartodasMovimentacoes();
    }

    @GetMapping("/data/{data}")
    public List<MovimentacaoDto> listarMovientacaoDataEspecifica(@PathVariable String data){
        return servicoMovimentacao.listarMovientacaoDataEspecifica(data);
    }
}
