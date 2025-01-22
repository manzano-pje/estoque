package com.api.estoque.excessoes;

import java.util.HashMap;
import java.util.Map;

public class ErroDeResposta extends RuntimeException{

    private String mensagem;
    private Integer codigo;

    public ErroDeResposta(String mensagem, Integer codigo){
        this.mensagem = mensagem;
        this.codigo = codigo;
    }

    public Map<String, Object> paraJson(){
        Map<String, Object> json = new HashMap<>();
        json.put("Código: ", this.codigo);
        json.put("Erro: ", this.mensagem);
        return json;
    }
}
