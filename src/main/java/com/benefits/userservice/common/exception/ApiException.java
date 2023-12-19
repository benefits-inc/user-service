package com.benefits.userservice.common.exception;

import com.benefits.userservice.common.resultcode.ResultCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs{
    private final ResultCodeIfs resultCodeIfs;
    private final String errorMessage;

    public ApiException(ResultCodeIfs resultCodeIfs){
        super(resultCodeIfs.getMessage());
        this.resultCodeIfs = resultCodeIfs;
        this.errorMessage = resultCodeIfs.getMessage();
    }

    public ApiException(ResultCodeIfs resultCodeIfs, String description){
        super(resultCodeIfs.getMessage());
        this.resultCodeIfs = resultCodeIfs;
        this.errorMessage = description;
    }
}
