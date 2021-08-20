CREATE DATABASE IF NOT EXISTS `arm_cloud_api` DEFAULT CHARACTER SET = utf8mb4;
Use `arm_cloud_api`;

CREATE TABLE `sys_user`
(
    `id`          INTEGER     NOT NULL AUTO_INCREMENT,
    `username`    VARCHAR(64) NOT NULL UNIQUE COMMENT '用户名称',
    `password`    VARCHAR(64) NOT NULL COMMENT '用户密码',
    `phone`       VARCHAR(64) COMMENT '电话号码',
    `email`       VARCHAR(64) COMMENt '电子邮箱',
    `status`      INTEGER     NOT NULL DEFAULT 0 COMMENT '用户状态',
    `create_time` TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
);

CREATE TABLE `sys_role`
(
    `id`          INTEGER     NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64) NOT NULL UNIQUE COMMENT '角色名称',
    `desc`        VARCHAR(255) COMMENT '角色描述',
    `create_time` TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
);

CREATE TABLE `sys_user_role`
(
    `id`          INTEGER NOT NULL AUTO_INCREMENT,
    `user_id`     INTEGER NOT NULL COMMENT '用户标识',
    `role_id`     INTEGER NOT NULL COMMENT '角色标识',
    `create_time` TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
);

CREATE TABLE `sys_permission`
(
    `id`          INTEGER      NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(64)  NOT NULL UNIQUE COMMENT '权限名称',
    `content`     VARCHAR(255) NOT NULL COMMENT '授权内容',
    `desc`        VARCHAR(255) COMMENT '权限描述',
    `create_time` TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
);

CREATE TABLE `sys_role_permission`
(
    `id`            INTEGER NOT NULL AUTO_INCREMENT,
    `role_id`       INTEGER NOT NULL COMMENT '角色标识',
    `permission_id` INTEGER NOT NULL COMMENT '权限标识',
    `create_time`   TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id)
);

# Spring Security OAuth2
CREATE TABLE `oauth_client_details`
(
    client_id               VARCHAR(256) PRIMARY KEY COMMENT '必填，OAuth2 client_id',
    resource_ids            VARCHAR(256) COMMENT '可选，资源id集合，多个资源用英文逗号隔开',
    client_secret           VARCHAR(256) COMMENT '必填，OAuth2 client_secret',
    scope                   VARCHAR(256) COMMENT '必填，OAuth2 权限范围，比如 read，write等可自定义',
    authorized_grant_types  VARCHAR(256) COMMENT '必填，OAuth2 授权类型，支持类型：authorization_code,password,refresh_token,implicit,client_credentials，多个类型用英文逗号隔开',
    web_server_redirect_uri VARCHAR(256) COMMENT '可选，客户端的重定向URI,当grant_type为authorization_code或implicit时,此字段是必需的',
    authorities             VARCHAR(256) COMMENT '可选，指定客户端所拥有的Spring Security的权限值',
    access_token_validity   INTEGER COMMENT '可选，access_token的有效时间值(单位:秒)，RefreshTokenValiditySeconds默认12小时',
    refresh_token_validity  INTEGER COMMENT '可选，refresh_token的有效时间值(单位:秒)，RefreshTokenValiditySeconds默认30天',
    additional_information  VARCHAR(4096) COMMENT '预留字段，格式必须是json',
    autoapprove             VARCHAR(256) COMMENT '该字段适用于grant_type="authorization_code"的情况下，用户是否自动approve操作'
);
