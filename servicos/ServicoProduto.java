package com.api.estoque.servicos;

import com.api.estoque.configuracoes.FormatarTexto;
import com.api.estoque.dtos.AlteracaoProdutoDto;
import com.api.estoque.dtos.DadosProdutoCompletoDto;
import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.entidades.Produto;
import com.api.estoque.excessoes.ExcessaoNaoExistemProdutosCadastrados;
import com.api.estoque.excessoes.ExcessaoProdutoJaCadastrado;
import com.api.estoque.excessoes.ExcessaoProdutoNaoCadastrado;
import com.api.estoque.repositorios.RepositorioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ServicoProduto {

    private final RepositorioProduto repositorioProduto;
    private final ModelMapper mapper;

    public ProdutoDto criarProduto( ProdutoDto produtoDto){
        Optional<Produto> produtoOptional= repositorioProduto.findByCodProduto(produtoDto.getCodProduto());
        if (produtoOptional.isPresent()){
            throw new ExcessaoProdutoJaCadastrado();
        }
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto.setDataCadastro(LocalDate.now());
        repositorioProduto.save(produto);
        return mapper.map(produto, ProdutoDto.class);
    }
    public void alterarProdutoPorCodProduto(String codProduto, AlteracaoProdutoDto alteracaoProdutoDto){
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(codProduto);
        if(produtoOptional.isEmpty()){
            throw new ExcessaoProdutoNaoCadastrado();
        }
        Produto produto = produtoOptional.get();
        produto.setIdProduto(produto.getIdProduto());
        produto.setCodProduto(produto.getCodProduto());
        produto.setProduto(FormatarTexto.formatarString(alteracaoProdutoDto.getProduto()));
        produto.setQtdMinima(alteracaoProdutoDto.getQtdMinima());
        produto.setEstoque(alteracaoProdutoDto.getEstoque());
        produto.setValorCusto(alteracaoProdutoDto.getValorCusto());
        repositorioProduto.save(produto);
    }
    public List<DadosProdutoCompletoDto> listarTodosProdutos(){
        List<Produto> listaDeProdutos = repositorioProduto.findAll();
        if (listaDeProdutos.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }

        return listaDeProdutos
                .stream()
                .map(DadosProdutoCompletoDto::new)
                .collect(Collectors.toList());
    }
    public DadosProdutoCompletoDto listaUmProdutoPorCodigo(String codProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(codProduto.toUpperCase());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        Produto produto = produtoOptional.get();
        return  mapper.map(produto, DadosProdutoCompletoDto.class);
    }
    public DadosProdutoCompletoDto listaUmProdutoPorNome(String nomeProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByProduto(nomeProduto.toUpperCase());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        Produto produto = produtoOptional.get();
        return  mapper.map(produto, DadosProdutoCompletoDto.class);
    }
    public void excluirProdutoPorCod(String codProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(codProduto.toUpperCase());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoNaoExistemProdutosCadastrados();
        }
        repositorioProduto.deleteById(produtoOptional.get().getIdProduto());
    }
}