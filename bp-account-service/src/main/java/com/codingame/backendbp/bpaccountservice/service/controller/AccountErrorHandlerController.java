package com.codingame.backendbp.bpaccountservice.service.controller;

import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codingame.backendbp.bpaccountservice.exception.AsyncException;
import com.codingame.backendbp.bpaccountservice.exception.BadRequestException;
import com.codingame.backendbp.bpaccountservice.exception.NoFundsException;
import com.codingame.backendbp.bpaccountservice.exception.NotFoundException;
import com.codingame.backendbp.bpaccountservice.service.controller.model.BaseResponse;

@RestControllerAdvice
public class AccountErrorHandlerController extends ResponseEntityExceptionHandler {
    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountErrorHandlerController.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> badRequest(BadRequestException e) {
        LOGGER.error("Bad request exception", e);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(e.getMessage());
        baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        e.getErrors().getFieldErrors().stream().forEach(
                error -> baseResponse.addError(error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(baseResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResponse> dataIntegrityViolation(DataIntegrityViolationException e) {
        LOGGER.error("Data integrity violation exception", e);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new BaseResponse(HttpStatus.CONFLICT.value(), "El numero de cuenta ya existe"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse> notFoundException(NotFoundException e) {
        LOGGER.error("Not Found exception", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(NoFundsException.class)
    public ResponseEntity<BaseResponse> noFundsException(NoFundsException e) {
        LOGGER.error("No Funds exception", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
    
    @ExceptionHandler(AsyncException.class)
    public ResponseEntity<BaseResponse> asyncException(AsyncException e) {
        LOGGER.error("Async exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        LOGGER.error("Http Request Method Not Supported exception", ex);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new BaseResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "La llamada a la API no es correcta"));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        LOGGER.error("Http Message Not Readable exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse(HttpStatus.BAD_REQUEST.value(), "La llamada a la API no es correcta, revise los parametros y valores"));
   }
}
