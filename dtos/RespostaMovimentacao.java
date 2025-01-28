package com.api.estoque.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
public class RespostaMovimentacao<T> {
    private T objeto; // objeto da movimentaçãoDto
    private String mensagem; // Mensagem de retorno
}
