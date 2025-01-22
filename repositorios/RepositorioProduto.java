package com.api.estoque.repositorios;

import com.api.estoque.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioProduto extends JpaRepository<Produto, Integer> {

    @Query("SELECT p FROM Produto p WHERE p.codProduto = :codProduto OR p.produto = :produto")
    Optional<Produto> findByCodProdutoOrProduto(@Param("codProduto") String codProduto, @Param("produto") String produto);

    Optional<Produto> findByCodProduto(String codProduto);
    Optional<Produto> findByProduto(String produto);
}
