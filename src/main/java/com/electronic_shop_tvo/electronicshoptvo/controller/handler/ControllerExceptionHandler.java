package com.electronic_shop_tvo.electronicshoptvo.controller.handler;

import com.electronic_shop_tvo.electronicshoptvo.exception.*;
import com.electronic_shop_tvo.electronicshoptvo.model.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(INTERNAL_SERVER_ERROR.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> handleItemNotFoundException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(NOT_FOUND.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value = ItemTypeNotFoundException.class)
    public ResponseEntity<Object> handleItemTypeNotFoundException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(NOT_FOUND.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(NOT_FOUND.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value = PurchaseNotFoundException.class)
    public ResponseEntity<Object> handlePurchaseNotFoundException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(NOT_FOUND.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), NOT_FOUND, request);
    }

    @ExceptionHandler(value = QuantityIsNotValidException.class)
    public ResponseEntity<Object> handleQuantityIsNotValidException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(BAD_REQUEST.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), BAD_REQUEST, request);
    }

    @ExceptionHandler(value = QuantityIsUnderZeroException.class)
    public ResponseEntity<Object> handleQuantityIsUnderZeroException(Exception exception, WebRequest request) {
        ErrorDto error = new ErrorDto(BAD_REQUEST.getReasonPhrase(), exception.getMessage());

        return super.handleExceptionInternal(exception, error, new HttpHeaders(), BAD_REQUEST, request);
    }
}
