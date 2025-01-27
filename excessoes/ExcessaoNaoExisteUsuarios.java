package com.api.estoque.excessoes;

public class ExcessaoNaoExisteUsuarios extends ErroDeResposta {
    public ExcessaoNaoExisteUsuarios(){
        super("Não existem usuários cadastrados!", 404);
    }
}
