package com.hhrhub.authz.controller;

import com.hhrhub.authz.exception.BasicException;
import com.hhrhub.authz.core.model.Base;
import com.hhrhub.authz.core.common.PageInfo;
import com.hhrhub.authz.core.request.QueryRequest;
import com.hhrhub.authz.core.common.Result;
import com.hhrhub.authz.util.QueryWrapperVisitor;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cz.jirutka.rsql.parser.RSQLParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@Slf4j
public class BaseController<M extends IService<T>, T extends Base<T>> {

    protected M service;

    public BaseController(M service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Result<?> get(@PathVariable Long id) {
        T model = service.getById(id);
        if (Objects.isNull(model)) {
            throw new BasicException("Not found");
        }
        return Result.succeed(model);
    }

    @GetMapping
    public Result<?> query(QueryRequest request) {
        IPage<T> iPage = new Page<>(request.getPage(), request.getSize());
        if (StringUtils.isNotBlank(request.getQuery())) {
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            queryWrapper = new RSQLParser().parse(request.getQuery()).accept(new QueryWrapperVisitor<>(), queryWrapper);
            iPage = service.page(iPage, queryWrapper);
        } else {
            iPage = service.page(iPage);
        }
        return Result.succeed(new PageInfo<>(iPage.getCurrent(), iPage.getSize(),
                iPage.getPages(), iPage.getTotal(), iPage.getRecords()));
    }

    @PostMapping
    public Result<?> add(@RequestBody T body) {
        boolean created = service.save(body);
        if (created) {
            return Result.succeed(service.getById(body.getId()));
        } else {
            throw new BasicException("create failed");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean deleted = service.removeById(id);
        if (deleted) {
            return Result.succeed(service.getById(id));
        } else {
            throw new BasicException("delete failed");
        }
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody T body) {
        body.setId(id);
        boolean updated = service.updateById(body);
        if (updated) {
            return Result.succeed(service.getById(id));
        } else {
            throw new BasicException("update failed");
        }
    }

}
