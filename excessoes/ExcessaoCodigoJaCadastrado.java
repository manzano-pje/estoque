package com.api.estoque.excessoes;

public class ExcessaoCodigoJaCadastrado extends ErroDeResposta {
    public ExcessaoCodigoJaCadastrado(){
        super("Código de produto já cadastrado!",409);
    }
}
