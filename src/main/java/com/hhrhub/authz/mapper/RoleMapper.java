package com.hhrhub.authz.mapper;

import com.hhrhub.authz.core.model.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.hhrhub.authz.mapper.PermissionMapper.listByRoleId",
                            fetchType = FetchType.LAZY))
    })
    @Select("select * from sys_role where id = #{id}")
    Role selectById(Serializable id);
}
