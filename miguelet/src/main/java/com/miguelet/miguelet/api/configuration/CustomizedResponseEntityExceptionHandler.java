package com.miguelet.miguelet.api.configuration;


import com.miguelet.miguelet.core.exceptions.*;
import com.miguelet.miguelet.core.exceptions.specific_exception.*;

import lombok.extern.slf4j.*;
import org.springframework.beans.*;
import org.springframework.http.*;
import org.springframework.http.converter.*;
import org.springframework.validation.*;
import org.springframework.web.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.context.request.async.*;
import org.springframework.web.multipart.support.*;
import org.springframework.web.servlet.*;

import java.io.*;
import java.sql.*;
import java.util.Date;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler {
  public static final String CLASS = " Class --> ";
  public static final String LINEA = " linea -> ";


  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
      final Exception ex, final WebRequest request) {


    log.error("Exception 500" + ex);
    final StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));
    final String exceptionDetails = sw.toString();
    log.error(exceptionDetails);

    final ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
    log.error(exceptionResponse.toString());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public final ResponseEntity<String> handleAlRuntimeException(
      final RuntimeException ex, final WebRequest request) {
    printErrorTrace(ex);
    log.error("ERROR 500" + ex);
        final ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
    log.error(exceptionResponse.toString());
    showError(ex, request);
    return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
  }

  private void printErrorTrace(RuntimeException ex) {
    final StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));
    final String exceptionDetails = sw.toString();
    log.error(exceptionDetails);
  }



  @ExceptionHandler({
    HttpRequestMethodNotSupportedException.class,
    HttpMediaTypeNotSupportedException.class,
    HttpMediaTypeNotAcceptableException.class,
    MissingPathVariableException.class,
    MissingServletRequestParameterException.class,
    ServletRequestBindingException.class,
    ConversionNotSupportedException.class,
    TypeMismatchException.class,
    HttpMessageNotReadableException.class,
    HttpMessageNotWritableException.class,
    MethodArgumentNotValidException.class,
    MissingServletRequestPartException.class,
    BindException.class,
    NoHandlerFoundException.class,
    SQLSyntaxErrorException.class,
    AsyncRequestTimeoutException.class
  })
  public final ResponseEntity<ExceptionResponse> handlerAllException(
      final Exception ex, final WebRequest request) {
    final ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));
       showError(ex, request);
    exceptionResponse.setDetails(request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  ///// >> exception
  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<String> handleUserNotFoundException(
      final BadRequestException ex, final WebRequest request) {

    showError(ex, request);
    return new ResponseEntity<>(ex.getTextDescription(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoContentRequestException.class)
  public final ResponseEntity<String> handleUserNotFoundException(
          final NoContentRequestException ex, final WebRequest request) {

    showError(ex, request);
    return new ResponseEntity<>(ex.getTextDescription(), HttpStatus.NO_CONTENT);
  }

  private void showError(Exception ex, WebRequest request) {
    log.error(
        request.toString()
            + CLASS
            + ex.getStackTrace()[0].getClassName()
            + LINEA
            + ex.getStackTrace()[0].getLineNumber());
  }

  @ExceptionHandler(UnauthorizedException.class)
  public final ResponseEntity<String> handleUserUnauthorizedException(
      final UnauthorizedException ex, final WebRequest request) {
        showError(ex, request);
    return new ResponseEntity<>(ex.getTextDescription(), HttpStatus.UNAUTHORIZED);
  }


  @ExceptionHandler(GoneException.class)
  public final ResponseEntity<String> handleUserGoneException(
      final GoneException ex, final WebRequest request) {
        showError(ex, request);
    return new ResponseEntity<>(ex.getTextDescription(), HttpStatus.GONE);
  }

  @ExceptionHandler(ConflictRequestException.class)
  public final ResponseEntity<String> handleUserConflictRequestException(
      final ConflictRequestException ex, final WebRequest request) {
        showError(ex, request);
    return new ResponseEntity<>(ex.getTextDescription(), HttpStatus.CONFLICT);
  }





}
