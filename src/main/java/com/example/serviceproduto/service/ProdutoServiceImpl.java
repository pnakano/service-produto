package com.example.serviceproduto.service;

import com.example.serviceproduto.model.Produto;
import com.example.serviceproduto.repository.ProdutoRepository;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto one(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public Produto inserir(Produto produto) {
        return produtoRepository.save(produto);
    }

}
