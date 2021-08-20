package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName("sys_role")
public class Role extends Base<Role> {

    private static final long serialVersionUID = -1261930625835173610L;

    private String name;

    private String code;

    @TableField(exist = false)
    private List<Permission> permissions;
}
