package com.api.estoque.controles;

import com.api.estoque.dtos.AlteracaoProdutoDto;
import com.api.estoque.dtos.DadosProdutoCompletoDto;
import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.servicos.ServicoProduto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("api/v1")
public class ControleProduto {

    private  final ServicoProduto servicoProduto;

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody @Valid ProdutoDto produtoDto){
        servicoProduto.criarProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }

    @GetMapping
    public List<DadosProdutoCompletoDto> listarTodosProdutos(){
        return servicoProduto.listarTodosProdutos();
    }

    @GetMapping("/pesquisaCod/{codProduto}")
    public DadosProdutoCompletoDto listaUmProdutoPorCodigo(@PathVariable String codProduto){
        return servicoProduto.listaUmProdutoPorCodigo(codProduto);
    }

    @GetMapping("/pesquisaNome/{nomeProduto}")
    public DadosProdutoCompletoDto listaUmProdutoPorNome(@PathVariable String nomeProduto) {
        return servicoProduto.listaUmProdutoPorNome(nomeProduto);
    }

    @PatchMapping("/{codProduto}")
    public ResponseEntity<Object> alterarProdutoPorCodProduto(@RequestBody @Valid AlteracaoProdutoDto alteracaoProdutoDto,
                                                              @PathVariable String codProduto){
        servicoProduto.alterarProdutoPorCodProduto(codProduto, alteracaoProdutoDto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto alterado com sucesso!");
    }
    @DeleteMapping("/{codProduto}")
    public ResponseEntity<Object> excluirProdutoPorCod(@PathVariable String codProduto){
        servicoProduto.excluirProdutoPorCod(codProduto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso!");
    }
}
