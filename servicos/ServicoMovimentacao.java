package com.api.estoque.servicos;

import com.api.estoque.dtos.MovimentacaoDto;
import com.api.estoque.entidades.Movimentacao;
import com.api.estoque.entidades.Produto;
import com.api.estoque.entidades.Usuario;
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

import java.util.Date;
import java.util.Optional;

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
        if(movimentacaoDto.getQuantidade() > produtoOptional.get().getEstoque()){
            throw new ExcessaoQuantidadeInsuficiente();
        }

        Usuario usuario = mapper.map(usuarioOptional.get(), Usuario.class);
        Produto produto= mapper.map(produtoOptional.get(), Produto.class);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipoMovimentacao(movimentacaoDto.getTipoMovimentacao());
        movimentacao.setDataMovimentacao(new Date());
        movimentacao.setQuantidade(movimentacaoDto.getQuantidade());
        movimentacao.setProduto(produto);
        movimentacao.setUsuario(usuario);
        repositorioMovimentacao.save(movimentacao);

        int estoqueAtual = produto.getEstoque() - movimentacaoDto.getQuantidade();
        produto.setEstoque(estoqueAtual);
        repositorioProduto.save(produto);

        if(estoqueAtual < produto.getQtdMinima()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Movimentação efetuada com sucesso.  -  Estoque abaixo do mínimo.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Movimentação efetuada com sucesso.");
    }
}
