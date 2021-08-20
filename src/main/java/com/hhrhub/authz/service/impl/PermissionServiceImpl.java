package com.hhrhub.authz.service.impl;

import com.hhrhub.authz.mapper.PermissionMapper;
import com.hhrhub.authz.core.model.Permission;
import com.hhrhub.authz.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
