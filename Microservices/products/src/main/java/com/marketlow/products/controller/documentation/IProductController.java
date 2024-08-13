package com.marketlow.products.controller.documentation;

import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface IProductController {
    @Operation(summary = "Save Product Market Low")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Accepted", content = @Content(schema = @Schema(implementation = ResponseEntity.class ), mediaType = "application/json") )
    })
    @PostMapping()
    ResponseEntity<Object> saveProduct(@RequestBody Product product) throws CustomException;

    @Operation(summary = "Update Product Market Low")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Accepted", content = @Content(schema = @Schema(implementation = ResponseEntity.class ), mediaType = "application/json") )
    })
    @PutMapping()
    ResponseEntity<Object> updateProduct(@RequestBody Product product) throws CustomException;

    @Operation(summary = "Get Product For Uui id Market Low")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class ), mediaType = "application/json") )
    })
    @GetMapping("/{uui}")
    ResponseEntity<Object> findByUui(@PathVariable String uui) throws CustomException;

    @Operation(summary = "Get Product For All Active Products Market Low")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class ), mediaType = "application/json") )
    })
    @GetMapping("/active")
    ResponseEntity<Object> findAllActiveProducts() throws CustomException;


    @Operation(summary = "Get List Of All Products Market Low")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEntity.class ), mediaType = "application/json") )
    })
    @GetMapping()
    ResponseEntity<Object> findAllProducts() throws CustomException;

}
