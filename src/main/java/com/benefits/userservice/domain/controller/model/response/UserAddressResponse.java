package com.benefits.userservice.domain.controller.model.response;

import com.benefits.userservice.db.entity.address.enums.UserReceiveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddressResponse {
    private Long id; // 3개까지 가능하니 이 id를 갖고 수정
    private Long userId;
    private String receiver;
    private String address1;
    private String address2;
    private String phone;
    private UserReceiveType receiveType;
    private String receiveMessage;
}
