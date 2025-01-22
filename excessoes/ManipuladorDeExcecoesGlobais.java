package com.api.estoque.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManipuladorDeExcecoesGlobais {

    @ExceptionHandler({ExcessaoProdutoJaCadastrado.class})
    public ResponseEntity<Object> handleExcessaoProdutoJaCadastrado(ExcessaoProdutoJaCadastrado ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ExcessaoNaoExistemProdutosCadastrados.class})
    public ResponseEntity<Object> handleExcessaoNaoExistemProdutosCadastrados(ExcessaoNaoExistemProdutosCadastrados ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.NOT_FOUND);
    }
}
