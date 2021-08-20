package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName("sys_client")
@JsonIgnoreProperties(value = {"handler"})
public class Client extends Base<Client> {

    private static final long serialVersionUID = -8909311150642054291L;

    private String name;

    private String code;

    private String credential;

    @TableField(exist = false)
    private List<Permission> permissions;
}
