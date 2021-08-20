package com.hhrhub.authz.controller;

import com.hhrhub.authz.core.model.Group;
import com.hhrhub.authz.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/groups")
public class GroupController extends BaseController<GroupService, Group> {

    @Autowired
    public GroupController(GroupService service) {
        super(service);
    }
}
