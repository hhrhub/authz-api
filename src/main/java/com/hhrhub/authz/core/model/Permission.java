package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@TableName("sys_permission")
public class Permission extends Base<Permission> {

    private static final long serialVersionUID = -4216343787584388666L;

    private String name;

    private String code;

}
