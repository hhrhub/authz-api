package com.hhrhub.authz.core.request;

import lombok.Data;

@Data
public class QueryRequest {

    private String query;

    private Long size = 60L;

    private Long page = 1L;
}
