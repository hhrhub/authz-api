package com.hhrhub.authz.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GrantType {
    AUTHORIZATION_CODE("authorization_code", 0),
    CLIENT_CREDENTIALS("client_credentials", 1),
    PASSWORD("password", 2),
    IMPLICIT("implicit", 3),
    REFRESH_TOKEN("refresh_token", 4);

    private String name;
    private int value;
}
