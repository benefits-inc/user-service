package com.benefits.userservice.common.resultcode;

public interface ResultCodeIfs {
    Integer getHttpStatusCode();
    Integer getResultCode();
    String getMessage();
}
