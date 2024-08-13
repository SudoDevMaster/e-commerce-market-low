package com.marketlow.products.controller.rest;

import com.marketlow.products.application.port.IProductApplicationService;
import com.marketlow.products.controller.documentation.IProductController;
import com.marketlow.products.controller.rest.custom.ApiResponse;
import com.marketlow.products.domain.constant.ConstantsProperties;
import com.marketlow.products.domain.model.Product;
import com.marketlow.products.exception.custom.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ConstantsProperties.PROJECT_PATH)
@AllArgsConstructor
public class ProductController implements IProductController {
    private IProductApplicationService iProductApplicationService;
    private final String OK_RESPONSE = "OK";
    @Override
    public ResponseEntity<Object> saveProduct(Product product) throws CustomException {
        return new ResponseEntity<Object>(ApiResponse.success(OK_RESPONSE, iProductApplicationService.saveProduct(product)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateProduct(Product product) throws CustomException {
        return new ResponseEntity<Object>(ApiResponse.success(OK_RESPONSE, iProductApplicationService.updateProduct(product)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByUui(String uui) throws CustomException {
        return new ResponseEntity<Object>(ApiResponse.success(OK_RESPONSE, iProductApplicationService.findByUui(uui)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findAllActiveProducts() throws CustomException {
        return new ResponseEntity<Object>(ApiResponse.success(OK_RESPONSE, iProductApplicationService.findAllActiveProducts()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findAllProducts() throws CustomException {
        return new ResponseEntity<Object>(ApiResponse.success(OK_RESPONSE, iProductApplicationService.findAllProducts()), HttpStatus.OK);
    }
}
