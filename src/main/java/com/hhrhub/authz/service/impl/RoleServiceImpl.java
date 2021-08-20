package com.hhrhub.authz.service.impl;

import com.hhrhub.authz.mapper.RoleMapper;
import com.hhrhub.authz.core.model.Role;
import com.hhrhub.authz.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
