package com.hhrhub.authz.service.impl;

import com.hhrhub.authz.mapper.GroupMapper;
import com.hhrhub.authz.core.model.Group;
import com.hhrhub.authz.service.GroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
}
