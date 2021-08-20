package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_client_permission")
public class ClientPermission extends Base<ClientPermission> {

    private static final long serialVersionUID = 1906362631423699198L;

    private Long permissionId;

    private Long clientId;
}
