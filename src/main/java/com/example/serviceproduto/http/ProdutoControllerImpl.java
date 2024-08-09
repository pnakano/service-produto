package com.example.serviceproduto.http;

import com.example.serviceproduto.http.data.request.ProdutoPersistDto;
import com.example.serviceproduto.http.data.response.ProdutoResponseDto;
import com.example.serviceproduto.model.Produto;
import com.example.serviceproduto.service.ProdutoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoControllerImpl {

    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;

    public ProdutoControllerImpl(ProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDto inserir(@Valid @RequestBody ProdutoPersistDto dto){
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        Produto produtoPersistido = produtoService.inserir(produto);
        return modelMapper.map(produtoPersistido, ProdutoResponseDto.class);
    }

    @GetMapping("{id}")
    public ProdutoResponseDto findById(@PathVariable("id") Long id){
        var produto = produtoService.one(id);
        return modelMapper.map(produto, ProdutoResponseDto.class);
    }

}
