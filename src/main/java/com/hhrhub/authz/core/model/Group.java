package com.hhrhub.authz.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("sys_group")
public class Group extends Base<Group> {

    private static final long serialVersionUID = -5382830178372440839L;

    private String name;

    private String code;

    private String remark;

}
