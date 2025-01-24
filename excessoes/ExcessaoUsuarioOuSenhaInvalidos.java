package com.api.estoque.excessoes;

public class ExcessaoUsuarioOuSenhaInvalidos extends ErroDeResposta {
    public ExcessaoUsuarioOuSenhaInvalidos(){

        super("Usuário nou senha inválidos!", 422);
    }
}
