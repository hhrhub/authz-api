package com.hhrhub.authz.service.impl;

import com.hhrhub.authz.mapper.UserMapper;
import com.hhrhub.authz.core.model.User;
import com.hhrhub.authz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
