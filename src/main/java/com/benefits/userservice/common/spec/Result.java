package com.benefits.userservice.common.spec;

import com.benefits.userservice.common.resultcode.ResultCodeIfs;
import com.benefits.userservice.common.resultcode.UserResultCode;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema( description = "공통 응답을 위한 Result 객체")
public class Result {
    @Schema( type = "integer", example = "200")
    private Integer resultCode;

    @Schema( type = "string", example = "성공")
    private String resultMessage;

    @Schema( type = "string", example = "성공")
    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(UserResultCode.OK.getResultCode())
                .resultMessage(UserResultCode.OK.getMessage())
                .resultDescription("성공")
                .build();
    }
    public static Result CREATE(){
        return Result.builder()
                .resultCode(UserResultCode.CREATE.getResultCode())
                .resultMessage(UserResultCode.CREATE.getMessage())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ResultCodeIfs resultCodeIfs){
        return Result.builder()
                .resultCode(resultCodeIfs.getResultCode())
                .resultMessage(resultCodeIfs.getMessage())
                .resultDescription("실패")
                .build();
    }

    public static Result ERROR(ResultCodeIfs resultCodeIfs, String description){
        return Result.builder()
                .resultCode(resultCodeIfs.getResultCode())
                .resultMessage(resultCodeIfs.getMessage())
                .resultDescription(description)
                .build();
    }

}
