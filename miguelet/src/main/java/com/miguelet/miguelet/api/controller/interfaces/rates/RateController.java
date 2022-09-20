package com.miguelet.miguelet.api.controller.interfaces.rates;
import com.miguelet.miguelet.core.exceptions.specific_exception.*;
import com.miguelet.miguelet.model.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;


@Tag(name = "rates", description = "Available operations for rates domain.")

@RestController
//@RequestMapping("/secured/")
public interface RateController {
    @Operation(summary = "Get rate list",operationId = "getRates")
    @Tag(name = "currencies")
    @GetMapping(path = "v1/rates")
    Page<RatesDto> getRatessAll(@RequestParam(required = false,defaultValue = "") String q,Pageable pageable);

    @Operation(summary = "add Rate",operationId = "addRate")
    @Tag(name = "Rate")
    @PostMapping(path = "v1/rates")
    long addRate(@RequestBody RatesDto rates) throws BadRequestException;

    @Operation(summary = "get rate by ID",operationId = "getRateById")
    @Tag(name = "Rate")
    @GetMapping(path = "v1/rates/{idRate}")
    RatesDto getRateById(@PathVariable long idRate) throws NoContentRequestException, BadRequestException;

    @Operation(summary = "delete rate by ID",operationId = "deleteRate")
    @Tag(name = "Rate")
    @DeleteMapping (path = "v1/rates/{idRate}")
    String deleteRate(@PathVariable long idRate) throws NoContentRequestException;

    @Operation(summary = "update Rate",operationId = "updateRate")
    @Tag(name = "Rate")
    @PutMapping(path = "v1/rates")
    RatesDto updateRate(@RequestBody RatesDto rates) throws NoContentRequestException, BadRequestException;
}
