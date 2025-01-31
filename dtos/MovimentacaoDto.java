package com.api.estoque.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import com.api.estoque.entidades.enums.TipoMovimentacao;

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
    private TipoMovimentacao TipoMovimentacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NonNull
    private Date dataMovimentacao;
    @NonNull
    private int quantidade;

}
