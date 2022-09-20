package com.miguelet.miguelet.core.services.impl;

import com.miguelet.miguelet.core.exceptions.specific_exception.*;
import com.miguelet.miguelet.core.mapper.*;
import com.miguelet.miguelet.core.services.interfaces.*;
import com.miguelet.miguelet.model.*;
import com.miguelet.miguelet.persistence.entity.*;
import com.miguelet.miguelet.persistence.repository.*;
import com.turkraft.springfilter.boot.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RatesServiceImpl implements RatesService {
    RatesRepository ratesRepository;
    RateMapper rateMapper;

    // private functions
    private Rates getById(Long idRate) throws NoContentRequestException, BadRequestException {
        if (idRate == null) {
            throw new BadRequestException("id de la tarifa vacio");
        }
        return ratesRepository.findById(idRate).orElseThrow(() -> new NoContentRequestException(""));
    }

    private void correctRate(Rates rate) throws BadRequestException {
        val isCorrectCurrency = rate.getCurrency() != null;
        val isCorrectBrand = rate.getBrand() != null;
        val isCorrectProduct = rate.getProduct() != null;
        if (!(isCorrectCurrency | isCorrectBrand | isCorrectProduct)) {
            throw new BadRequestException("incorrecto");
        }
    }

    //public funcitions
    @Override
    public long addRate(RatesDto rateAdd) throws BadRequestException {
        rateAdd.setId(null);
        val rate = rateMapper.rateFromRateDto(rateAdd);
        correctRate(rate);
        return ratesRepository.saveAndFlush(rate).getId();

    }


    @Override
    public String deleteRate(long idRate) throws NoContentRequestException {
        // si pudieramos tener diferentes tiendas no pondría este metodo si no que recuperariamos
        // el objeto para hacer las comprobaciones pertinentes.
        if (ratesRepository.existsById(idRate)) {
            ratesRepository.deleteById(idRate);
        }else {
            throw new NoContentRequestException("");
        }
        return "borrado correctamente";
    }

    @Override
    public RatesDto getRateById(long idRate) throws NoContentRequestException, BadRequestException {
        return rateMapper.rateDtoFromRate(this.getById(idRate));
    }

    @Override
    public RatesDto updateRate(RatesDto rateUpdate) throws NoContentRequestException, BadRequestException {
        this.getById(rateUpdate.getId());
        val rate = rateMapper.rateFromRateDto(rateUpdate);
        this.correctRate(rate);
        return rateMapper.rateDtoFromRate(ratesRepository.saveAndFlush(rate));
    }

    @Override
    public Page<RatesDto> getAll(String q, Pageable pageable) {
        final Specification<Rates> spec = new FilterSpecification<>(q);
        return ratesRepository.findAll(spec, pageable).map(rateMapper::rateDtoFromRate);
    }
}
