package com.zy.service.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @create 2023-02-44 11:47:56
 * @describe 用户表实体类
 */
@Data
@ToString
@TableName("sys_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Schema(name = "SysUser对象", description = "用户表")
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;


    @SchemaProperty(name = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @SchemaProperty(name = "登录账号")
    @TableField("username")
    private String username;

    @SchemaProperty(name = "真实姓名")
    @TableField("realname")
    private String realname;

    @SchemaProperty(name = "密码")
    @TableField("password")
    private String password;

    @SchemaProperty(name = "md5密码盐")
    @TableField("salt")
    private String salt;

    @SchemaProperty(name = "头像")
    @TableField("avatar")
    private String avatar;

    @SchemaProperty(name = "生日")
    @TableField("birthday")
    private Date birthday;

    @SchemaProperty(name = "性别(0-默认未知,1-男,2-女)")
    @TableField("sex")
    private Boolean sex;

    @SchemaProperty(name = "电子邮件")
    @TableField("email")
    private String email;

    @SchemaProperty(name = "电话")
    @TableField("phone")
    private String phone;

    @SchemaProperty(name = "机构编码")
    @TableField("org_code")
    private String orgCode;

    @SchemaProperty(name = "状态(1-正常,2-冻结)")
    @TableField("status")
    private Boolean status;

    @SchemaProperty(name = "删除状态(0-正常,1-已删除)")
    @TableField("del_flag")
    @TableLogic
    private Boolean delFlag;

    @SchemaProperty(name = "第三方登录的唯一标识")
    @TableField("third_id")
    private String thirdId;

    @SchemaProperty(name = "第三方类型")
    @TableField("third_type")
    private String thirdType;

    @SchemaProperty(name = "同步工作流引擎(1-同步,0-不同步)")
    @TableField("activiti_sync")
    private Boolean activitiSync;

    @SchemaProperty(name = "工号，唯一键")
    @TableField("work_no")
    private String workNo;

    @SchemaProperty(name = "职务，关联职务表")
    @TableField("post")
    private String post;

    @SchemaProperty(name = "座机号")
    @TableField("telephone")
    private String telephone;

    @SchemaProperty(name = "创建人")
    @TableField("create_by")
    private String createBy;

    @SchemaProperty(name = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @SchemaProperty(name = "更新人")
    @TableField("update_by")
    private String updateBy;

    @SchemaProperty(name = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @SchemaProperty(name = "身份（1普通成员 2上级）")
    @TableField("user_identity")
    private Boolean userIdentity;

    @SchemaProperty(name = "负责部门")
    @TableField("depart_ids")
    private String departIds;

    @SchemaProperty(name = "设备ID")
    @TableField("client_id")
    private String clientId;

    @SchemaProperty(name = "是否运营用户")
    @TableField("is_admin")
    private Boolean isAdmin;

    @SchemaProperty(name = "支付密码")
    @TableField("pay_passwd")
    private String payPasswd;

    @SchemaProperty(name = "支付密码盐值")
    @TableField("pay_salt")
    private String paySalt;

    @SchemaProperty(name = "无密码支付")
    @TableField("pay_ignore_pwd")
    private Boolean payIgnorePwd;


}
