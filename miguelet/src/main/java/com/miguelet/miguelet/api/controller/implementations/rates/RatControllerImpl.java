package com.miguelet.miguelet.api.controller.implementations.rates;

import com.miguelet.miguelet.api.controller.interfaces.rates.*;
import com.miguelet.miguelet.core.exceptions.specific_exception.*;
import com.miguelet.miguelet.core.services.interfaces.*;
import com.miguelet.miguelet.model.*;
import lombok.*;
import lombok.experimental.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/")
@RestController
public class RatControllerImpl implements RateController {
    RatesService ratesService;
    @Override
    public Page<RatesDto> getRatessAll(String q, Pageable pageable) {
        return ratesService.getAll(q,pageable);
    }

    @Override
    public long addRate(RatesDto rates) throws BadRequestException {
        return ratesService.addRate(rates);
    }

    @Override
    public RatesDto getRateById(long idRate) throws NoContentRequestException, BadRequestException {
        return ratesService.getRateById(idRate);
    }

    @Override
    public String deleteRate(long idRate) throws NoContentRequestException {
        return ratesService.deleteRate(idRate);
    }

    @Override
    public RatesDto updateRate(RatesDto rates) throws NoContentRequestException, BadRequestException {
        return ratesService.updateRate(rates);
    }
}
