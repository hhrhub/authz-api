package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_role_permission")
public class RolePermission extends Base<RolePermission> {

    private static final long serialVersionUID = 248936663194021106L;

    private Long permissionId;

    private Long roleId;
}
