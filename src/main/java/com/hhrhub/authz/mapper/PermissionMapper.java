package com.hhrhub.authz.mapper;

import com.hhrhub.authz.core.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select * from sys_permission where id in " +
            "(select permission_id from sys_role_permission where role_id = #{id})")
    List<Permission> listByRoleId(Serializable id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @Select("select * from sys_permission where id in " +
            "(select permission_id from sys_client_permission where client_id = #{id})")
    List<Permission> listByClientId(Serializable id);
}
