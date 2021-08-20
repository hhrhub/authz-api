package com.hhrhub.authz.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultType {

    SUCCESS("success", 0),

    INVALID_REQUEST("invalid_request", 1),

    UNAUTHORIZED_CLIENT("unauthorized_client", 2),

    ACCESS_DENIED("access_denied", 3),

    UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type", 4),

    INVALID_SCOPE("invalid_scope", 5),

    SERVER_ERROR("server_error", 6),

    TEMPORARILY_UNAVAILABLE("temporarily_unavailable", 7),

    INVALID_CODE("invalid_code", 8),

    INVALID_CREDENTIAL("invalid_credential", 9),

    INVALID_REDIRECT_URL("invalid_redirect_url", 10),

    INVALID_GRANT("invalid_grant", 11);

    private String msg;
    private Integer code;
}
