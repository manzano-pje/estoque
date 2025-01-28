package com.api.estoque.excessoes;

public class ExcessaoProdutoNaoCadastrado extends ErroDeResposta {
    public ExcessaoProdutoNaoCadastrado(){
        super("Produto inexistente!", 404);
    }
}
