package com.example.serviceproduto.http;

import com.example.serviceproduto.http.data.request.ProdutoPersistDto;
import com.example.serviceproduto.model.Produto;
import com.github.fge.jsonpatch.JsonPatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface ProdutoController {

    @Operation(summary = "Salva o produto de acordo com o JSON passado por parâmetro")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Produto save(@Valid @RequestBody ProdutoPersistDto dto);

    @Operation(summary = "Retorna o produto correspondente ao identificador recuperado por parâmetro")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_100",
                                                "mensagem": "Produto de código {id} não encontrado",
                                                "documentacao": null
                                            }
                                            """
                            )
                    )
            )
    })
    @GetMapping("{id}")
    Produto findById(@PathVariable("id") Long id);

    @Operation(summary = "Atualiza os atributos passados no body para o produto passado por parâmetro")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_100",
                                                "mensagem": "Produto de código {id} não encontrado",
                                                "documentacao": null
                                            }
                                            """
                            )
                    )
            )
    })
    @PatchMapping("{id}")
    Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch patch);

    @Operation(summary = "Deleta o produto passado por parâmetro")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_100",
                                                "mensagem": "Produto de código {id} não encontrado",
                                                "documentacao": null
                                            }
                                            """
                            )
                    )
            )
    })
    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id);

    @Operation(summary = "Atualiza todos os campos do produto passado por parâmetro, de acordo com o JSON fornecido")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                                            {
                                                "codigo": "X_100",
                                                "mensagem": "Produto de código {id} não encontrado",
                                                "documentacao": null
                                            }
                                            """
                            )
                    )
            )
    })
    @PutMapping("{id}")
    Produto updateAll(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto);

}
