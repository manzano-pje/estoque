package com.api.estoque.servicos;

import com.api.estoque.configuracoes.FormatarTexto;
import com.api.estoque.dtos.AtualizarQuantidadeDto;
import com.api.estoque.dtos.DadosProdutoCompletoDto;
import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.entidades.Produto;
import com.api.estoque.excessoes.ExcessaoNaoExistemProdutosCadastrados;
import com.api.estoque.repositorios.RepositorioProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> verificarProduto(String codigoProduto){
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(codigoProduto);
        if(produtoOptional.isPresent()){
            return ResponseEntity.ok(new AtualizarQuantidadeDto("Atualizar quantidade do produto. " + produtoOptional.get().getProduto(), 0));
        }else{
            DadosProdutoCompletoDto dadosProdutoCompletoDto = new DadosProdutoCompletoDto();
            dadosProdutoCompletoDto.setCodProduto(codigoProduto);
            return ResponseEntity.ok("Cadastrar novo produto" + dadosProdutoCompletoDto);
        }
    }

    public ProdutoDto criarProduto(String codProduto, ProdutoDto ProdutoDto){
        Produto produto = new Produto();
        repositorioProduto.save(produto);
        return mapper.map(produto, ProdutoDto.class);
    }

    public void alterarProdutoPorCodProduto(String codProduto, ProdutoDto produtoDto){
        Produto produto = autalizarPrduto(codProduto, produtoDto);
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

    private Produto autalizarPrduto(String codProduto, ProdutoDto novoProdutoDto){
        Produto produto = new Produto();
        produto.setCodProduto(codProduto);
        produto.setProduto(FormatarTexto.formatarString(novoProdutoDto.getProduto()));
        produto.setQtdMinima(novoProdutoDto.getQtdMinima());
        produto.setEstoque(novoProdutoDto.getEstoque());
        produto.setValorCusto(novoProdutoDto.getValorCusto());
        return produto;
    }

}
