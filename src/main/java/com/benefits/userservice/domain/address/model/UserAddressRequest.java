package com.benefits.userservice.domain.address.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String receiver;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

    @NotBlank
    private String phone;

    @NotBlank
    private String receiveType;

    @NotBlank(message = "배송 메시지는 필수 입니다")
    private String receiveMessage;
}
