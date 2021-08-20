package com.hhrhub.authz.controller;

import com.hhrhub.authz.core.model.Role;
import com.hhrhub.authz.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/roles")
public class RoleController extends BaseController<RoleService, Role> {

    public RoleController(RoleService service) {
        super(service);
    }
}
