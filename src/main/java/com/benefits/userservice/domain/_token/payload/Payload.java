package com.benefits.userservice.domain._token.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payload {
    private String sub;
    private String role;
    private String email;
    private String exp;
}
