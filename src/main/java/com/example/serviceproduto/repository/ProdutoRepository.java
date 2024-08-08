package com.example.serviceproduto.repository;

import com.example.serviceproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {



}
