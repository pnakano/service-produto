package com.example.serviceproduto.service;

import com.example.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto findById(Long id);

    void delete(Long id);

    Produto update(Produto produto);
}
