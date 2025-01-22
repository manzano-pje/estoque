package com.api.estoque.servicos;

import com.api.estoque.configuracoes.FormatarTexto;
import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.entidades.Produto;
import com.api.estoque.excessoes.ExcessaoCodigoJaCadastrado;
import com.api.estoque.excessoes.ExcessaoProdutoJaCadastrado;
import com.api.estoque.repositorios.RepositorioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class ServicoProduto {

    private final RepositorioProduto repositorioProduto;
    private final ModelMapper mapper;

    public ProdutoDto criarProduto(ProdutoDto produtoDto) throws ExcessaoCodigoJaCadastrado {
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProdutoOrProduto(produtoDto.getCodProduto(), produtoDto.getProduto());
        if(produtoOptional.isPresent()){
            throw new ExcessaoProdutoJaCadastrado();
        }
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto.setProduto(FormatarTexto.formatarString(produtoDto.getProduto()));
        produto.setCodProduto(produtoDto.getCodProduto().toUpperCase());
        repositorioProduto.save(produto);
        return mapper.map(produto, ProdutoDto.class);
    }



}
