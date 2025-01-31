package com.api.estoque.servicos;

import com.api.estoque.dtos.MovimentacaoDto;
import com.api.estoque.entidades.Movimentacao;
import com.api.estoque.entidades.Produto;
import com.api.estoque.entidades.Usuario;
import com.api.estoque.entidades.enums.TipoMovimentacao;
import com.api.estoque.excessoes.ExcessaoNaoExisteMovimentacaoNestaData;
import com.api.estoque.excessoes.ExcessaoNaoExisteUsuarios;
import com.api.estoque.excessoes.ExcessaoProdutoNaoCadastrado;
import com.api.estoque.excessoes.ExcessaoQuantidadeInsuficiente;
import com.api.estoque.repositorios.RepositorioMovimentacao;
import com.api.estoque.repositorios.RepositorioProduto;
import com.api.estoque.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class ServicoMovimentacao {

    private final RepositorioMovimentacao repositorioMovimentacao;
    private final RepositorioProduto repositorioProduto;
    private final RepositorioUsuario repositorioUsuario;
    private final ModelMapper mapper;

    public ResponseEntity<Object> criarMovimentacao(MovimentacaoDto movimentacaoDto){
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsuario(movimentacaoDto.getUsuario());
        if(usuarioOptional.isEmpty()){
            throw  new ExcessaoNaoExisteUsuarios();
        }
        Optional<Produto> produtoOptional = repositorioProduto.findByCodProduto(movimentacaoDto.getCodProduto());
        if(produtoOptional.isEmpty()){
            throw new ExcessaoProdutoNaoCadastrado();
        }

        Usuario usuario = usuarioOptional.get();
        Produto produto = produtoOptional.get();

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipoMovimentacao(movimentacaoDto.getTipoMovimentacao());
        movimentacao.setDataMovimentacao(LocalDate.now());
        movimentacao.setProduto(produto);
        movimentacao.setUsuario(usuario);
        movimentacao.setQuantidade(movimentacaoDto.getQuantidade());

        int estoqueAtual = produto.getEstoque();
        if(movimentacaoDto.getTipoMovimentacao() == TipoMovimentacao.VENDA){
            if(movimentacaoDto.getQuantidade() > produtoOptional.get().getEstoque()){
                throw new ExcessaoQuantidadeInsuficiente();
            }
            estoqueAtual = estoqueAtual - movimentacaoDto.getQuantidade();
            produto.setEstoque(estoqueAtual);
        }else{
            produto.setEstoque(estoqueAtual + movimentacaoDto.getQuantidade());
        }
        repositorioProduto.save(produto);
        repositorioMovimentacao.save(movimentacao);

        if(estoqueAtual < produto.getQtdMinima()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Movimentação efetuada com sucesso.  -  Estoque abaixo do mínimo.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Movimentação efetuada com sucesso.");
    }

    public List<MovimentacaoDto> listartodasMovimentacoes(){
        List<Movimentacao> listaMovimentacao = repositorioMovimentacao.findAll();
        if(listaMovimentacao.isEmpty()){
            throw new ExcessaoNaoExisteMovimentacaoNestaData();
        }
        return listaMovimentacao.stream()
                .map(MovimentacaoDto::new)
                .collect(Collectors.toList());
    }

    public List<MovimentacaoDto> listarMovientacaoDataEspecifica(String data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatter);
        List<Movimentacao> listaMovimentacao = repositorioMovimentacao.findByDataMovimentacao(dataFormatada);
        if(listaMovimentacao.isEmpty()){
            throw new ExcessaoNaoExisteMovimentacaoNestaData();
        }
        return listaMovimentacao.stream()
                .map(MovimentacaoDto::new)
                .collect(Collectors.toList());
    }

    public List<MovimentacaoDto> listarMovientacaoDataEspecifica(LocalDate dataInicio, LocalDate dataFinal){

        List<Movimentacao> listaMovimentacao = repositorioMovimentacao.findByDataMovimentacaoBetween(dataInicio, dataFinal);
        if(listaMovimentacao.isEmpty()){
            throw new ExcessaoNaoExisteMovimentacaoNestaData();
        }
        return listaMovimentacao.stream()
                .map(MovimentacaoDto::new)
                .collect(Collectors.toList());

    }
}
