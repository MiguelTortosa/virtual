package com.miguelet.miguelet.api.configuration;

import org.springframework.core.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.http.server.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

@ControllerAdvice
class NoContentPageControllerAdvice implements ResponseBodyAdvice<Page<?>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Page.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Page<?> beforeBodyWrite(Page<?> body, MethodParameter returnType, MediaType selectedContentType,
                                   Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                   ServerHttpRequest request, ServerHttpResponse response) {
        if ( null == body || body.isEmpty()) {
            response.setStatusCode(HttpStatus.NO_CONTENT);
        }
        return body;
    }
}

