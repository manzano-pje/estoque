package com.api.estoque.excessoes;

public class ExcessaoNaoExistemProdutosCadastrados extends ErroDeResposta {
    public ExcessaoNaoExistemProdutosCadastrados(){
        super("Não existem produtos cadastrados!",404);
    }
}
