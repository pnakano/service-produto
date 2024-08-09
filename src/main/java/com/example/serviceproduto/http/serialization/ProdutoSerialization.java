package com.example.serviceproduto.http.serialization;

import com.example.serviceproduto.http.data.response.ProdutoResponseDto;
import com.example.serviceproduto.model.Produto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ProdutoSerialization  extends JsonSerializer<Produto> {

    private final ModelMapper modelMapper;

    public ProdutoSerialization(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void serialize(Produto produto, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ProdutoResponseDto produtoResponseDto = modelMapper.map(produto, ProdutoResponseDto.class);
        jsonGenerator.writeObject(produtoResponseDto);
    }
}
