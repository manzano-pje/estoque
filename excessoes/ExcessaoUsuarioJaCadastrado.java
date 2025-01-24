package com.api.estoque.excessoes;

public class ExcessaoUsuarioJaCadastrado extends ErroDeResposta {
    public ExcessaoUsuarioJaCadastrado(){
        super("Usuário já cadastrado!", 409);
    }
}
