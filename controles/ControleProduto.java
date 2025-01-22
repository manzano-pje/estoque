package com.api.estoque.controles;

import com.api.estoque.dtos.ProdutoDto;
import com.api.estoque.servicos.ServicoProduto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("api/v1/")
public class ControleProduto {

    private  final ServicoProduto servicoProduto;

    @PostMapping
    public ResponseEntity<Object> criarProduto(@RequestBody @Valid ProdutoDto produtoDto){
        servicoProduto.criarProduto(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
    }

    @GetMapping
    public List<ProdutoDto> listarTodosProdutos(){
        return servicoProduto.listarTodosProdutos();
    }

}
