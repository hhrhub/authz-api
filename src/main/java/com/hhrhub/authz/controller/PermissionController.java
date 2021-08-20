package com.hhrhub.authz.controller;

import com.hhrhub.authz.core.model.Permission;
import com.hhrhub.authz.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/permissions")
public class PermissionController extends BaseController<PermissionService, Permission> {

    @Autowired
    public PermissionController(PermissionService service) {
        super(service);
    }
}
