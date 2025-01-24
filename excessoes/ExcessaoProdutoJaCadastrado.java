package com.api.estoque.excessoes;

public class ExcessaoProdutoJaCadastrado extends ErroDeResposta {
    public ExcessaoProdutoJaCadastrado(){

        super("Produto já cadastrado!", 409);
    }
}
