package com.zy.service.sys.entity;

import java.io.Serializable;
import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
//import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @auther zy-栀
 * @create 2023-02-44 13:57:03
 * @describe 系统日志表实体类
 */
@Data
@ToString
//@Table("sys_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "SysLog对象", description = "系统日志表")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    @SchemaProperty(name = "日志类型（1登录日志，2操作日志）")
    private Integer logType;

    @SchemaProperty(name = "日志内容")
    private String logContent;

    @SchemaProperty(name = "操作类型")
    private Integer operateType;

    @SchemaProperty(name = "操作用户账号")
    private String userid;

    @SchemaProperty(name = "操作用户名称")
    private String username;

    @SchemaProperty(name = "IP")
    private String ip;

    @SchemaProperty(name = "请求java方法")
    private String method;

    @SchemaProperty(name = "请求路径")
    private String requestUrl;

    @SchemaProperty(name = "请求参数")
    private String requestParam;

    @SchemaProperty(name = "请求类型")
    private String requestType;

    @SchemaProperty(name = "耗时")
    private Long costTime;

    @SchemaProperty(name = "创建人")
    private String createBy;

    @SchemaProperty(name = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createTime;

    @SchemaProperty(name = "更新人")
    private String updateBy;

    @SchemaProperty(name = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant updateTime;

    @SchemaProperty(name = "ADMIN-1;")
    private String clientType;


}
