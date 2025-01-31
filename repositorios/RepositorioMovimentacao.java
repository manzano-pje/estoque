package com.api.estoque.repositorios;

import com.api.estoque.entidades.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepositorioMovimentacao extends JpaRepository<Movimentacao, Integer> {
    List<Movimentacao> findByDataMovimentacao(LocalDate data);
}
