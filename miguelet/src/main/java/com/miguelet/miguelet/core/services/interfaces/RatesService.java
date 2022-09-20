package com.miguelet.miguelet.core.services.interfaces;

import com.miguelet.miguelet.core.exceptions.specific_exception.*;
import com.miguelet.miguelet.model.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;


public interface RatesService {

    long addRate(RatesDto rate) throws BadRequestException;
    String deleteRate(long idRate) throws NoContentRequestException;
    RatesDto getRateById(long idRate) throws NoContentRequestException, BadRequestException;
    Page<RatesDto> getAll(String q, Pageable pageable);
    RatesDto updateRate(RatesDto rate) throws NoContentRequestException, BadRequestException;
}
