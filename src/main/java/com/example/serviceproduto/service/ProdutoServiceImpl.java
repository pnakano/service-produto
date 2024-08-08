package com.example.serviceproduto.service;

import com.example.serviceproduto.model.Produto;
import com.example.serviceproduto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto inserir(Produto produto) {
        return produtoRepository.save(produto);
    }

}
