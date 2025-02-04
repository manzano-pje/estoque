package com.api.estoque.dtos;

import com.api.estoque.entidades.Movimentacao;
import com.api.estoque.entidades.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import com.api.estoque.entidades.enums.TipoMovimentacao;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDto {

    private String usuario;
    @NonNull
    private String codProduto;
    @Enumerated(EnumType.STRING)
    private TipoMovimentacao TipoMovimentacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NonNull
    private LocalDate dataMovimentacao;
    @NonNull
    private int quantidade;

    public MovimentacaoDto(Movimentacao movimentacao) {
        this.usuario = movimentacao.getUsuario().getUsuario();
        this.codProduto = movimentacao.getProduto().getCodProduto();
        this.dataMovimentacao = movimentacao.getDataMovimentacao();
        this.quantidade = movimentacao.getQuantidade();
        this.TipoMovimentacao = movimentacao.getTipoMovimentacao();
    }
}
