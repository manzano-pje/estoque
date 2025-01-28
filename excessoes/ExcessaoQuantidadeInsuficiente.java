package com.api.estoque.excessoes;

public class ExcessaoQuantidadeInsuficiente extends ErroDeResposta {
    public ExcessaoQuantidadeInsuficiente(){
        super("Quantidade insufiente no estoque.",422);
    }
}
