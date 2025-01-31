package com.api.estoque.excessoes;

public class ExcessaoNaoExisteMovimentacaoNestaData extends ErroDeResposta {
    public ExcessaoNaoExisteMovimentacaoNestaData(){
        super("Não existem movimentações nesta data.",404);
    }
}
