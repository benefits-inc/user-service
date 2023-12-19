package com.benefits.userservice.domain.model;

import com.benefits.userservice.entity.address.enums.UserReceiveType;
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
    private UserReceiveType receiveType;

    @NotBlank
    private String receiveMessage;
}
