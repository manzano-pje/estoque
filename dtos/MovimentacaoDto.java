package com.api.estoque.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDto {

    @NotBlank
    private String usuario;
    @NotBlank
    private String produto;
    private int idMovimentacao;
    @Enumerated(EnumType.STRING)
    private com.api.estoque.entidades.enums.TipoMovimentacao TipoMovimentacao;
    @NotBlank
    private Date dataMovimentacao;
    @NotBlank
    private double valor;
}
