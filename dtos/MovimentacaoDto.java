package com.api.estoque.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDto {

    @NonNull
    private String usuario;
    @NonNull
    private String codProduto;
    @Enumerated(EnumType.STRING)
    private com.api.estoque.entidades.enums.TipoMovimentacao TipoMovimentacao;
    @NonNull
    private Date dataMovimentacao;
    @NonNull
    private double valor;
    @NonNull
    private int quantidade;

}
