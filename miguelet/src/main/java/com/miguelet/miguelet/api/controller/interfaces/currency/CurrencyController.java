package com.miguelet.miguelet.api.controller.interfaces.currency;
import com.miguelet.miguelet.model.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;


@Tag(name = "currencies", description = "Available operations for currencies domain.")

@RestController
@RequestMapping("/")
public interface CurrencyController {

    @Operation(summary = "Get currencies list",operationId = "getCurrencies")
    @Tag(name = "currencies")
    @GetMapping(path = "v1/currencies")
    Page<CurrencyDto> getAllCurrency(@RequestParam(required = false) String q,Pageable pageable);
}
