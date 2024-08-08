package com.example.serviceproduto.http.data.request;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class ProdutoPersistDto {

    private String descricao;

    private BigDecimal valor;

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
