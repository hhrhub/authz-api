package com.hhrhub.authz.controller;

import com.hhrhub.authz.core.model.User;
import com.hhrhub.authz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController extends BaseController<UserService, User> {

    @Autowired
    public UserController(UserService service) {
        super(service);
    }

}
