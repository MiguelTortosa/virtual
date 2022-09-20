package com.miguelet.miguelet.model;


import lombok.*;



@Data
public class CurrencyDto {

    private String code;
    private String symbol;
    private Integer decimals;
}
