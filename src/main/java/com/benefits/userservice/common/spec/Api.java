package com.benefits.userservice.common.spec;

import com.benefits.userservice.common.resultcode.ResultCodeIfs;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Api<T> {
    private Result result;

    //@Valid
    private T data;
    private Pagination pagination;

    public static <T> Api<T> OK(T data){
        return Api.<T>builder()
                .data(data)
                .result(Result.OK())
                .build();
    }

    public static <T> Api<T> CREATE(T data){
        return Api.<T>builder()
                .data(data)
                .result(Result.CREATE())
                .build();
    }

    public static <T> Api<T> OK(T data, Pagination pagination){
        return Api.<T>builder()
                .data(data)
                .pagination(pagination)
                .result(Result.OK())
                .build();
    }


    public static Api<Object> ERROR(Result result){
        return Api.builder()
                .result(result)
                .build();
    }

    public static Api<Object> ERROR(ResultCodeIfs resultCodeIfs){
        return Api.builder()
                .result(Result.ERROR(resultCodeIfs))
                .build();
    }
    public static Api<Object> ERROR(ResultCodeIfs resultCodeIfs, String description){
        return Api.builder()
                .result(Result.ERROR(resultCodeIfs, description))
                .build();
    }
}
