package com.hhrhub.authz.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {

    private long page;

    private long size;

    private long pages;

    private long total;

    private Collection<T> data;
}
