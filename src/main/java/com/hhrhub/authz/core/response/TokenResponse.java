package com.hhrhub.authz.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -7952313604873017443L;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private long expireIn;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("refresh_token")
    private String refreshToken;
}
