package com.benefits.userservice.exceptionhandler;

import com.benefits.userservice.common.resultcode.ServerResultCode;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.benefits.userservice.common.spec.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
//@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception ex){
        log.error("Global Exception: ", ex);
        return ResponseEntity.status(500)
                .body(Api.ERROR(ServerResultCode.SERVER_ERROR, ex.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Api<Object>> validationException(MethodArgumentNotValidException ex){
        log.error("MethodArgumentNotValidException Exception: ", ex);
        var resultMessage = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            log.error(fieldError.getDefaultMessage());
            return fieldError.getDefaultMessage();
        }).toArray();

        return ResponseEntity.status(ex.getStatusCode())
                .body(Api.ERROR(UserResultCode.BAD_REQUEST, Arrays.toString(resultMessage)));
    }

}
