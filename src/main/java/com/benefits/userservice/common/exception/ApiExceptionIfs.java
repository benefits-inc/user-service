package com.benefits.userservice.common.exception;

import com.benefits.userservice.common.resultcode.ResultCodeIfs;

public interface ApiExceptionIfs {
    ResultCodeIfs getResultCodeIfs();
    String getErrorMessage();

}
