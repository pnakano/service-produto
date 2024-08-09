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
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", id));
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", produto.getId()));
        }

        return produtoRepository.save(produto);
    }

}
