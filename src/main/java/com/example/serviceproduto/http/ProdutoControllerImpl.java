package com.example.serviceproduto.http;

import com.example.serviceproduto.http.data.request.ProdutoPersistDto;
import com.example.serviceproduto.model.Produto;
import com.example.serviceproduto.service.ProdutoService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@Valid @RequestBody ProdutoPersistDto dto){
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        return produtoService.save(produto);
    }

    @Override
    @GetMapping("{id}")
    public Produto findById(@PathVariable("id") Long id){
        return produtoService.findById(id);
    }

    @Override
    @PatchMapping("{id}")
    public Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch patch){
        Produto produto = produtoService.findById(id);

        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

        try {
            JsonNode produtoJsonNode = objectMapper.convertValue(produto, JsonNode.class);
            JsonNode patchJsonNode = patch.apply(produtoJsonNode);
            Produto produtoPersist = objectMapper.treeToValue(patchJsonNode, Produto.class);
            return produtoService.save(produtoPersist);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        produtoService.delete(id);
    }

    @Override
    @PutMapping("{id}")
    public Produto updateAll(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto){
        Produto produto = new Produto(id, dto.getDescricao(), dto.getValor());
        return produtoService.update(produto);
    }

}
