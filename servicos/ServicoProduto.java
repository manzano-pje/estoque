package com.api.estoque.servicos;

import com.api.estoque.configuracoes.FormatarTexto;
import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.entidades.Produto;
import com.api.estoque.excessoes.ExcessaoNaoExistemProdutosCadastrados;
import com.api.estoque.excessoes.ExcessaoProdutoJaCadastrado;
import com.api.estoque.repositorios.RepositorioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ServicoProduto {

    private final RepositorioProduto repositorioProduto;
    private final ModelMapper mapper;

    public ProdutoDto criarProduto(ProdutoDto produtoDto){
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

    public List<ProdutoDto> listarTodosProdutos(){
        List<Produto> listaDeProdutos = repositorioProduto.findAll();
        if (listaDeProdutos.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        return listaDeProdutos
                .stream()
                .map(ProdutoDto::new)
                .collect(Collectors.toList());
    }

    public ProdutoDto listaUmProdutoPorCodigo(String codProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(codProduto.toUpperCase());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        Produto produto = produtoOptional.get();
        return  mapper.map(produto, ProdutoDto.class);
    }

    public ProdutoDto listaUmProdutoPorNome(String nomeProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByProduto(nomeProduto.toUpperCase());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        Produto produto = produtoOptional.get();
        return  mapper.map(produto, ProdutoDto.class);
    }


}
