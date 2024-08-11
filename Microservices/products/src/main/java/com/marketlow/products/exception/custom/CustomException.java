package com.marketlow.products.exception.custom;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomException extends RuntimeException{
    private String message;
}
