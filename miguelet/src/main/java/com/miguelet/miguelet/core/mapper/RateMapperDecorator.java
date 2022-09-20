package com.miguelet.miguelet.core.mapper;

import com.miguelet.miguelet.model.*;
import com.miguelet.miguelet.persistence.entity.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;


public abstract class RateMapperDecorator  implements RateMapper{
    @Qualifier("delegate")
   @Autowired
    private  RateMapper delegate;
    @Override
    public RatesDto rateDtoFromRate(Rates rates) {
       val dto = delegate.rateDtoFromRate(rates);
       val currency =  rates.getCurrency();
       dto.setPrice(String.format("%."+currency.getDecimals()+"f", rates.getPrice().doubleValue()));
        return dto;
    }
}
