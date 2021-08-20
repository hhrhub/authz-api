package com.hhrhub.authz.service.impl;

import com.hhrhub.authz.mapper.ClientMapper;
import com.hhrhub.authz.core.model.Client;
import com.hhrhub.authz.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {
}
