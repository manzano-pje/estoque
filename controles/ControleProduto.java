package com.api.estoque.controles;

import com.api.estoque.dtos.AtualizarQuantidadeDto;
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

    @PostMapping("/{codProduto}")
    public ResponseEntity<?> verificarProduto(@PathVariable String codProduto){
        return servicoProduto.verificarProduto(codProduto);
    }

    @PostMapping("/cadastrar/{codProduto}")
    public ResponseEntity<Object> criarProduto(@PathVariable String codProduto, @RequestBody @Valid ProdutoDto produtoDto){
        servicoProduto.criarProduto(codProduto, produtoDto);
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
    public ResponseEntity<Object> alterarProdutoPorCodProduto(@RequestBody @Valid ProdutoDto produtoDto,
                                                              @PathVariable String codProduto){
        servicoProduto.alterarProdutoPorCodProduto(codProduto, produtoDto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto alterado com sucesso!");
    }


    @DeleteMapping("/{codProduto}")
    public ResponseEntity<Object> excluirProdutoPorCod(@PathVariable String codProduto){
        servicoProduto.excluirProdutoPorCod(codProduto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso!");
    }
}
