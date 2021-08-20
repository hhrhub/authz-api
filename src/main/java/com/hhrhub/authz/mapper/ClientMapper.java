package com.hhrhub.authz.mapper;

import com.hhrhub.authz.core.model.Client;
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
public interface ClientMapper extends BaseMapper<Client> {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "code", column = "code"),
            @Result(property = "credential", column = "credential"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time"),
            @Result(property = "permissions", column = "id", javaType = List.class,
                    many = @Many(select = "com.hhrhub.authz.mapper.PermissionMapper.listByClientId",
                            fetchType = FetchType.LAZY))
    })
    @Select("select * from sys_client where id = #{id}")
    Client selectById(Serializable id);
}
