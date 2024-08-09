package com.example.serviceproduto.service;

import com.example.serviceproduto.event.ProdutoPersistEvent;
import com.example.serviceproduto.model.Produto;
import com.example.serviceproduto.repository.ProdutoRepository;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.produtoRepository = produtoRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Produto findById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public Produto save(Produto produto) {
        Produto produtoPersist = produtoRepository.save(produto);
        applicationEventPublisher.publishEvent(new ProdutoPersistEvent(this, produtoPersist));
        return produtoPersist;
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

        return save(produto);
    }

}
