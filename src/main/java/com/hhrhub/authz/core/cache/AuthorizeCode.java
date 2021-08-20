package com.hhrhub.authz.core.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeCode implements Serializable {

    private static final long serialVersionUID = -2203219907108128193L;

    private String code;

    private String state;

    private Long clientId;

    private String redirectUrl;

    private List<String> scope;
}
