package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@TableName("sys_user")
public class User extends Base<User> {

    private static final long serialVersionUID = -3526680087807302972L;

    private String name;

    private String password;

    private String phoneNumber;

    private Timestamp lastLoginTime;

    private Long roleId;

    private Long groupId;

}
