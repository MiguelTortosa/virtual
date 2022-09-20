package com.miguelet.miguelet.core.mapper;

import com.miguelet.miguelet.model.*;
import com.miguelet.miguelet.persistence.entity.*;
import org.mapstruct.*;
import org.springframework.stereotype.*;

@Service
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(RateMapperDecorator.class)

public interface RateMapper {
  @Mapping(target = "price", ignore = true)
  @Mapping(target = "brand", source = "brand.id")
  @Mapping(target = "product", source = "product.id")
  @Mapping(target = "currency", source = "currency.code")
  RatesDto rateDtoFromRate(Rates rates);
  @Mapping(target = "brand.id", source = "brand")
  @Mapping(target = "product.id", source = "product")
  @Mapping(target = "currency.code", source = "currency")
    Rates rateFromRateDto(RatesDto retes);


}
