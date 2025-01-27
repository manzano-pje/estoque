package com.api.estoque.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler({ExcessaoUsuarioJaCadastrado.class})
    public ResponseEntity<Object> handleExcessaoUsuarioJaCadastrado(ExcessaoUsuarioJaCadastrado ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ExcessaoUsuarioOuSenhaInvalidos.class})
    public ResponseEntity<Object> handleExcessaoUsuarioNaoPodeSerNulo(ExcessaoUsuarioOuSenhaInvalidos ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({ExcessaoUsuarioNaoCadastrado.class})
    public ResponseEntity<Object> handleExcessaoUsuarioNaoCadastrado(ExcessaoUsuarioNaoCadastrado ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ExcessaoNaoExisteUsuarios.class})
    public ResponseEntity<Object> handleExcessaoNaoExisteUsuarios(ExcessaoNaoExisteUsuarios ex){
        return new ResponseEntity<>(ex.paraJson(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Variável para armazenar a primeira mensagem de erro
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .findFirst()
                .orElse("Erro de validação");

        // Retorna a resposta com a primeira mensagem de erro
        ErroDeResposta erro = new ErroDeResposta(mensagem, 400);
        return new ResponseEntity<>(erro.paraJson(), HttpStatus.BAD_REQUEST);
    }


}
