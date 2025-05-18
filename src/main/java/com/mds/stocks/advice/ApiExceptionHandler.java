package com.mds.stocks.advice;

import com.mds.stocks.advice.exception.BadRequestException;
import com.mds.stocks.advice.exception.CompanyNotFoundException;
import com.mds.stocks.dto.exception.ApiException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value={CompanyNotFoundException.class})
    public ResponseEntity<ApiException> handleCompanyNotFound(CompanyNotFoundException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage(),"404",404, LocalDateTime.now()), HttpStatusCode.valueOf(404));
    }
    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<ApiException> handleBadRequestFound(BadRequestException e) {
        return new ResponseEntity<>(new ApiException(e.getMessage(),"400",400, LocalDateTime.now()), HttpStatusCode.valueOf(400));
    }
}