package com.miguelet.miguelet.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;


@Data
public class RatesDto {
    private Long id;
    private String brand;
    private String product;
    private String currency;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    private String price;
}
