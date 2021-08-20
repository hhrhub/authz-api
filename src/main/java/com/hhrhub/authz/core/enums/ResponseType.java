package com.hhrhub.authz.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseType {
    CODE("code", 0),
    TOKEN("token", 1);

    private String name;
    private int value;
}
