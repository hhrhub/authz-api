package com.hhrhub.authz.controller;

import com.hhrhub.authz.core.model.Client;
import com.hhrhub.authz.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/clients")
public class ClientController extends BaseController<ClientService, Client> {

    @Autowired
    public ClientController(ClientService service) {
        super(service);
    }
}
