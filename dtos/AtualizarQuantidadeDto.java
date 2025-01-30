package com.api.estoque.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarQuantidadeDto {

    private String mensagem;
    private int quantidade;
}
