package com.zy.service.sys.entity;

import java.io.Serializable;
import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @auther zy-栀
 * @create 2023-02-44 11:47:56
 * @describe 用户表实体类
 */
@Data
@ToString
@Table("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "SysUser对象", description = "用户表")
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;


    @SchemaProperty(name = "主键id")
    @Id
    private String id;

    @SchemaProperty(name = "登录账号")
    private String username;

    @SchemaProperty(name = "真实姓名")
    private String realname;

    @SchemaProperty(name = "密码")
    private String password;

    @SchemaProperty(name = "md5密码盐")
    private String salt;

    @SchemaProperty(name = "头像")
    private String avatar;

    @SchemaProperty(name = "生日")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Instant birthday;

    @SchemaProperty(name = "性别(0-默认未知,1-男,2-女)")
    private Boolean sex;

    @SchemaProperty(name = "电子邮件")
    private String email;

    @SchemaProperty(name = "电话")
    private String phone;

    @SchemaProperty(name = "机构编码")
    private String orgCode;

    @SchemaProperty(name = "状态(1-正常,2-冻结)")
    private Boolean status;

    @SchemaProperty(name = "删除状态(0-正常,1-已删除)")
    private Boolean delFlag;

    @SchemaProperty(name = "第三方登录的唯一标识")
    private String thirdId;

    @SchemaProperty(name = "第三方类型")
    private String thirdType;

    @SchemaProperty(name = "同步工作流引擎(1-同步,0-不同步)")
    private Boolean activitiSync;

    @SchemaProperty(name = "工号，唯一键")
    private String workNo;

    @SchemaProperty(name = "职务，关联职务表")
    private String post;

    @SchemaProperty(name = "座机号")
    private String telephone;

    @SchemaProperty(name = "创建人")
    private String createBy;

    @SchemaProperty(name = "创建时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Instant createTime;

    @SchemaProperty(name = "更新人")
    private String updateBy;

    @SchemaProperty(name = "更新时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Instant updateTime;

    @SchemaProperty(name = "身份（1普通成员 2上级）")
    private Boolean userIdentity;

    @SchemaProperty(name = "负责部门")
    private String departIds;

    @SchemaProperty(name = "设备ID")
    private String clientId;

    @SchemaProperty(name = "是否运营用户")
    private Boolean isAdmin;

    @SchemaProperty(name = "支付密码")
    private String payPasswd;

    @SchemaProperty(name = "支付密码盐值")
    private String paySalt;

    @SchemaProperty(name = "无密码支付")
    private Boolean payIgnorePwd;


}
