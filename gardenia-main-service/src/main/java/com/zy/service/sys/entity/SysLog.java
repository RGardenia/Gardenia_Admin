package com.zy.service.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;
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
@TableName("sys_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "SysLog对象", description = "系统日志表")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @SchemaProperty(name = "日志类型（1登录日志，2操作日志）")
    @TableField("log_type")
    private Integer logType;

    @SchemaProperty(name = "日志内容")
    @TableField("log_content")
    private String logContent;

    @SchemaProperty(name = "操作类型")
    @TableField("operate_type")
    private Integer operateType;

    @SchemaProperty(name = "操作用户账号")
    @TableField("userid")
    private String userid;

    @SchemaProperty(name = "操作用户名称")
    @TableField("username")
    private String username;

    @SchemaProperty(name = "IP")
    @TableField("ip")
    private String ip;

    @SchemaProperty(name = "请求java方法")
    @TableField("method")
    private String method;

    @SchemaProperty(name = "请求路径")
    @TableField("request_url")
    private String requestUrl;

    @SchemaProperty(name = "请求参数")
    @TableField("request_param")
    private String requestParam;

    @SchemaProperty(name = "请求类型")
    @TableField("request_type")
    private String requestType;

    @SchemaProperty(name = "耗时")
    @TableField("cost_time")
    private Long costTime;

    @SchemaProperty(name = "创建人")
    @TableField("create_by")
    private String createBy;

    @SchemaProperty(name = "创建时间")
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @SchemaProperty(name = "更新人")
    @TableField("update_by")
    private String updateBy;

    @SchemaProperty(name = "更新时间")
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @SchemaProperty(name = "ADMIN-1;")
    @TableField("client_type")
    private String clientType;


}
