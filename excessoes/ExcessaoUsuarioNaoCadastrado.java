package com.api.estoque.excessoes;

public class ExcessaoUsuarioNaoCadastrado extends ErroDeResposta {
    public ExcessaoUsuarioNaoCadastrado(){
        super("Usuário não cadastrado!", 404);
    }
}
