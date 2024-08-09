package com.example.serviceproduto.http;

import com.example.serviceproduto.http.data.request.ProdutoPersistDto;
import com.example.serviceproduto.http.data.response.ProdutoResponseDto;
import com.example.serviceproduto.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

public interface ProdutoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponseDto inserir(@Valid @RequestBody ProdutoPersistDto dto);

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
    public ProdutoResponseDto findById(@PathVariable("id") Long id);

}
