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
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@Valid @RequestBody ProdutoPersistDto dto){
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        return produtoService.inserir(produto);
    }

    @GetMapping("{id}")
    public Produto findById(@PathVariable("id") Long id){
        return produtoService.findById(id);
    }

}
