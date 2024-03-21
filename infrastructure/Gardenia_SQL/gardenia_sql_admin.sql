DROP DATABASE IF EXISTS `Gardenia_admin`;
CREATE DATABASE `Gardenia_admin`;
USE `Gardenia_admin`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '主键id',
  `username` varchar(100) DEFAULT NULL COMMENT '登录账号',
  `realname` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) DEFAULT NULL COMMENT 'md5密码盐',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0-默认未知,1-男,2-女)',
  `email` varchar(45) DEFAULT NULL COMMENT '电子邮件',
  `phone` varchar(45) DEFAULT NULL COMMENT '电话',
  `org_code` varchar(64) DEFAULT NULL COMMENT '机构编码',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态(1-正常,2-冻结)',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除状态(0-正常,1-已删除)',
  `third_id` varchar(100) DEFAULT NULL COMMENT '第三方登录的唯一标识',
  `third_type` varchar(100) DEFAULT NULL COMMENT '第三方类型',
  `activiti_sync` tinyint(1) DEFAULT NULL COMMENT '同步工作流引擎(1-同步,0-不同步)',
  `work_no` varchar(100) DEFAULT NULL COMMENT '工号，唯一键',
  `post` varchar(100) DEFAULT NULL COMMENT '职务，关联职务表',
  `telephone` varchar(45) DEFAULT NULL COMMENT '座机号',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `user_identity` tinyint(1) DEFAULT NULL COMMENT '身份（1普通成员 2上级）',
  `depart_ids` varchar(255) DEFAULT NULL COMMENT '负责部门',
  `client_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
  `is_admin` tinyint(1) DEFAULT NULL COMMENT '是否运营用户',
  `pay_passwd` varchar(255) DEFAULT NULL COMMENT '支付密码',
  `pay_salt` varchar(45) DEFAULT NULL COMMENT '支付密码盐值',
  `pay_ignore_pwd` tinyint(1) DEFAULT NULL COMMENT '无密码支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_sys_user_work_no` (`work_no`),
  UNIQUE KEY `uniq_sys_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(11) DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(255) DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(255) DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(255) DEFAULT NULL COMMENT 'IP',
  `method` varchar(255) DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `request_param` varchar(255) DEFAULT NULL COMMENT '请求参数',
  `request_type` varchar(255) DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `client_type` varchar(255) DEFAULT NULL COMMENT 'ADMIN-1;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置编号',
  `key` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置键',
  `name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '配置值',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '删除',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('1', 'web_name', '网站名称', '网站名称', NULL, '2021-03-02 16:08:19', '1', '2021-05-03 01:13:58', '网站名称', b'0', b'1');
INSERT INTO `sys_config` VALUES ('2', 'web_desc', '网站描述', '网站描述', NULL, '2021-03-02 16:08:19', NULL, '2021-03-02 16:08:19', '网站描述', NULL, b'0');
INSERT INTO `sys_config` VALUES ('3', 'web_auth', '网站作者', '网站作者', NULL, '2021-03-02 16:08:19', NULL, '2021-03-02 16:08:19', '网站作者', NULL, b'1');

-- ----------------------------
-- Table structure for sys_data_source
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_source`;
CREATE TABLE `sys_data_source`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识',
  `username` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账户',
  `password` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  `driver` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '驱动',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '是否启用',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_data_source
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门名称',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '状态',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `parent` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父级部门',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '顶级部门', '山东济南', b'1', 1, '0', NULL, NULL, '1', '2021-05-04 17:41:21', b'0', '部门备注', '1');
INSERT INTO `sys_dept` VALUES ('2', '北京分部', '山东济南', b'1', 1, '1', NULL, NULL, NULL, NULL, NULL, '部门备注', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识',
  `enable` bit(1) NOT NULL COMMENT '状态',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', 'sex', b'1', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict` VALUES ('2', '状态', 'enable', b'1', '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `label` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `value` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '值',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '状态',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('1', '男', '0', b'1', 'sex', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('1390004614516674561', '女', '1', b'1', 'sex', '1', '2021-05-06 02:05:00', '1', '2021-05-06 02:05:00', b'0', NULL, 0);
INSERT INTO `sys_dict_data` VALUES ('3', '开启', 'yes', b'1', 'enable', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dict_data` VALUES ('4', '禁用', 'no', b'1', 'enable', '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bean_name` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cron_expression` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `enable` bit(1) NULL DEFAULT NULL,
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES ('1391457610156937218', '异常任务', 'failure', NULL, '0/10 * * * * ?', b'1', '1', '2021-05-10 02:18:41', '1', '2021-08-02 19:54:20', '任务描述', b'0');
INSERT INTO `sys_job` VALUES ('1422975874129162242', '简单任务', 'simple', NULL, '0/10 * * * * ?', b'1', '1', '2021-08-05 01:41:01', '1', '2021-08-05 01:41:01', '任务描述', b'0');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `job_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `error` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `name` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路径',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '存储方式',
  `size` int(0) NULL DEFAULT NULL COMMENT '大小',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `suffix` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '后缀',
  `bucket` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '桶',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------
INSERT INTO `sys_oss` VALUES ('1', '头像', '0', '23', 10000, NULL, NULL, NULL, NULL, '备注', 'png', 'pearAdmin', NULL);
INSERT INTO `sys_oss` VALUES ('1390861513252319233', 'car.jpg', 'local', NULL, 0, '1', '2021-05-08 10:50:00', '1', '2021-05-08 10:50:00', NULL, '.jpg', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390862658586763265', 'car.jpg', 'local', NULL, 0, '1', '2021-05-08 10:54:33', '1', '2021-05-08 10:54:33', NULL, '.jpg', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390862717382516738', 'pear.png', 'local', NULL, 0, '1', '2021-05-08 10:54:47', '1', '2021-05-08 10:54:47', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390863589160239105', 'pear.png', 'local', NULL, 0, '1', '2021-05-08 10:58:15', '1', '2021-05-08 10:58:15', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390865041886748673', 'pear.png', 'local', 'D://upload\\2021\\5\\8\\7df98ed1305d46d28696623a6f357ee8.png', 0, '1', '2021-05-08 11:04:02', '1', '2021-05-08 11:04:02', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390865731312873474', 'pear.png', 'local', 'D:\\\\upload\\2021\\5\\8\\d0b598cfc0ea4aa9a57117b7a54b27b9.png', 0, '1', '2021-05-08 11:06:46', '1', '2021-05-08 11:06:46', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390865820110483458', 'pear.png', 'local', 'D:\\\\upload\\2021\\5\\8\\e8682f25a88041ff9ed423d567fa5c9f.png', 0, '1', '2021-05-08 11:07:07', '1', '2021-05-08 11:07:07', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390866058808324097', 'pear.png', 'local', 'D:\\\\upload\\2021\\5\\8\\74d1a2a158b94a4183eff61567adec3b.png', 0, '1', '2021-05-08 11:08:04', '1', '2021-05-08 11:08:04', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390868551709691906', 'pear.png', 'local', 'D:\\\\upload\\2021\\5\\8\\6023296446674ccca9e7f91273469d7b.png', 0, '1', '2021-05-08 11:17:58', '1', '2021-05-08 11:17:58', NULL, '.png', 'default', b'0');
INSERT INTO `sys_oss` VALUES ('1390904482890203137', 'pear.png', 'local', 'D:\\\\upload\\2021\\5\\8\\aac52f2952dc4c70a2a46d88af9f4f9e.png', 0, '1', '2021-05-08 13:40:45', '1', '2021-05-08 13:40:45', NULL, '.png', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390904823329275905', 'car.jpg', 'local', 'D:\\\\upload\\2021\\5\\8\\3bd9756857b34fb399038c357e751084.jpg', 0, '1', '2021-05-08 13:42:06', '1', '2021-05-08 13:42:06', NULL, '.jpg', NULL, b'0');
INSERT INTO `sys_oss` VALUES ('1390905375119355906', 'pear.png', 'aliyun', 'pear-admin.oss-cn-beijing.aliyuncs.comf28813b3-096a-479e-92ee-cdb73821b21a', 0, '1', '2021-05-08 13:44:18', '1', '2021-05-08 13:44:18', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390906443366641665', 'pear.png', 'aliyun', 'pear-admin.oss-cn-beijing.aliyuncs.comc92ecdd7-2f49-400b-8c44-013c78f2e5fb', 0, '1', '2021-05-08 13:48:33', '1', '2021-05-08 13:48:33', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390926198395809793', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/default/5265d155-a69f-4b9c-be33-c9e27b6149b6.png', 0, '1', '2021-05-08 15:07:03', '1', '2021-05-08 15:07:03', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390929293238538242', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/default/e10e9fcc-35aa-49c5-8e39-b59526bddda1.png', 0, '1', '2021-05-08 15:19:20', '1', '2021-05-08 15:19:20', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390929544687063041', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/default/5ff9543b-f482-4865-a727-931f2ce5e90f.png', 0, '1', '2021-05-08 15:20:20', '1', '2021-05-08 15:20:20', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390930574313193473', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/default/5d1efa73-5f77-446d-a629-054fecf907df.png', 56749, '1', '2021-05-08 15:24:26', '1', '2021-05-08 15:24:26', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390931718452215810', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/default/798223b6-d184-4e6f-8f47-547a571f9342.png', 0, '1', '2021-05-08 15:28:59', '1', '2021-05-08 15:28:59', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390932192052027393', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/default/3bb0a87e-1b78-468c-ae4e-6b922b18a2c2.png', 55, '1', '2021-05-08 15:30:52', '1', '2021-05-08 15:30:52', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390933867391229953', 'pear.png', 'aliyun', 'http://pear-admin-pro.oss-cn-beijing.aliyuncs.com/886c39df-917d-41ac-810e-a7433d51dddf.png', 55, '1', '2021-05-08 15:37:31', '1', '2021-05-08 15:37:31', NULL, '.png', 'pear-admin-pro', b'0');
INSERT INTO `sys_oss` VALUES ('1390935203306508290', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/b3a00f98-f2b7-49fe-9f3c-af9e14523699.png', 55, '1', '2021-05-08 15:42:49', '1', '2021-05-08 15:42:49', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935268309831681', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/b217f2b3-4fe8-4bc3-a679-d5e5da45a6bb.png', 55, '1', '2021-05-08 15:43:05', '1', '2021-05-08 15:43:05', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935282465607681', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/0252b5d8-1e5f-4bfe-a43f-a98101972180.png', 55, '1', '2021-05-08 15:43:08', '1', '2021-05-08 15:43:08', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935297221169154', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/9065d254-a4df-426e-8587-d7fe7e2f269a.png', 55, '1', '2021-05-08 15:43:12', '1', '2021-05-08 15:43:12', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935311024623617', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/ccfdd498-bb57-4c2b-844c-c6be7aa9ee1c.png', 55, '1', '2021-05-08 15:43:15', '1', '2021-05-08 15:43:15', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935327898308610', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/069d8bf0-bbc9-4a15-8397-23dd8433fc68.png', 55, '1', '2021-05-08 15:43:19', '1', '2021-05-08 15:43:19', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935348181962754', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/18345605-77f6-43e6-9b31-38c2dd8665d0.png', 55, '1', '2021-05-08 15:43:24', '1', '2021-05-08 15:43:24', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390935386387877889', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/9cb1cd92-f711-4b96-8102-63a8ea4d09cf.png', 55, '1', '2021-05-08 15:43:33', '1', '2021-05-08 15:43:33', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390939723252076546', 'QQ图片20210508160029.jpg', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/ddc0a4e2-6865-42bc-9386-81f4b3b7ec87.jpg', 74, '1', '2021-05-08 16:00:47', '1', '2021-05-08 16:00:47', NULL, '.jpg', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390952707982581761', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/b3e6c1d3-4bcb-4ea5-acfb-0b7d00376ba8.png', 55, '1', '2021-05-08 16:52:23', '1', '2021-05-08 16:52:23', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390963921517244418', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/26bb177b-0a01-45b2-941e-f6838185f4d5.png', 55, '1', '2021-05-08 17:36:56', '1', '2021-05-08 17:36:56', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390963962021638145', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/a49c9bc5-67f8-48eb-9f66-84f8958fb120.png', 55, '1', '2021-05-08 17:37:06', '1', '2021-05-08 17:37:06', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390963994619768834', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/387df393-af46-4fad-b59a-e5f5ff8d8c2b.png', 55, '1', '2021-05-08 17:37:14', '1', '2021-05-08 17:37:14', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964008792322050', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/6fa5047f-b7ef-48b1-9fc1-f15963efc020.png', 55, '1', '2021-05-08 17:37:17', '1', '2021-05-08 17:37:17', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964024122503170', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/1d790683-25fb-445d-97e2-83c1136c2ff3.png', 55, '1', '2021-05-08 17:37:21', '1', '2021-05-08 17:37:21', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964055428788226', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/6d637881-a065-41a1-9994-eeaf67667100.png', 55, '1', '2021-05-08 17:37:28', '1', '2021-05-08 17:37:28', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964124823547905', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/8f5dbb1a-115c-4aeb-bc23-3aa1581d7679.png', 55, '1', '2021-05-08 17:37:45', '1', '2021-05-08 17:37:45', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964146315161601', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/7ed7e955-8994-43fd-8d57-1883b474c2d8.png', 55, '1', '2021-05-08 17:37:50', '1', '2021-05-08 17:37:50', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390964170830868481', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/1c749861-50e5-49ae-8df1-6bce28d76758.png', 55, '1', '2021-05-08 17:37:56', '1', '2021-05-08 17:37:56', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1390967143040520194', 'pear.png', 'local', 'D:\\\\upload\\60154306261b40e992047e8ab73106dd.png', 55, '1', '2021-05-08 17:49:44', '1', '2021-05-08 17:49:44', NULL, '.png', '默认', b'0');
INSERT INTO `sys_oss` VALUES ('1390967228579155969', 'pear.png', 'local', 'D:\\\\upload\\a9d89f5f901148c992c643787a6a35eb.png', 55, '1', '2021-05-08 17:50:05', '1', '2021-05-08 17:50:05', NULL, '.png', '默认', b'0');
INSERT INTO `sys_oss` VALUES ('1390967285147734017', 'pear.png', 'local', 'D:\\\\upload\\60d8acd6c6454d32b145dc328e5a61d1.png', 55, '1', '2021-05-08 17:50:18', '1', '2021-05-08 17:50:18', NULL, '.png', '默认', b'0');
INSERT INTO `sys_oss` VALUES ('1390967338134376449', 'pear.png', 'local', 'D:\\\\upload\\6ec7671d47d44c09b7708a646a7d9925.png', 55, '1', '2021-05-08 17:50:31', '1', '2021-05-08 17:50:31', NULL, '.png', '默认', b'0');
INSERT INTO `sys_oss` VALUES ('1391081199009193986', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/5b844976-d69c-4a13-a617-de77a045616c.png', 55, '1', '2021-05-09 01:22:58', '1', '2021-05-09 01:22:58', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081215140487170', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/5151d373-248f-446b-8266-397bcf6a61c8.png', 55, '1', '2021-05-09 01:23:01', '1', '2021-05-09 01:23:01', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081236007149569', 'image.jpg', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/d8e80054-e2de-45df-b106-63b72212a18d.jpg', 74, '1', '2021-05-09 01:23:06', '1', '2021-05-09 01:23:06', NULL, '.jpg', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081281792172033', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/b4f46fb0-85df-4382-a60c-7dd4bc88d014.png', 55, '1', '2021-05-09 01:23:17', '1', '2021-05-09 01:23:17', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081296476430338', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/cdc5e8fc-42be-4f89-a4d6-9e0ae681e07e.png', 55, '1', '2021-05-09 01:23:21', '1', '2021-05-09 01:23:21', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081310787395586', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/28da62ca-a561-44c8-8ea4-cd2f81ce8f9b.png', 55, '1', '2021-05-09 01:23:24', '1', '2021-05-09 01:23:24', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081323684880386', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/2a1501b0-cc2a-438c-b77e-2dca73c51749.png', 55, '1', '2021-05-09 01:23:27', '1', '2021-05-09 01:23:27', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081335546372098', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/2c20a0a3-bbb6-4d8e-a7b1-e6a9b5bc845c.png', 55, '1', '2021-05-09 01:23:30', '1', '2021-05-09 01:23:30', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081353594462209', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/70317218-c45b-4978-8c62-87b6417c6c59.png', 55, '1', '2021-05-09 01:23:34', '1', '2021-05-09 01:23:34', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081366110265346', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/f738eb33-be25-4eed-8872-971fe2458aa0.png', 55, '1', '2021-05-09 01:23:37', '1', '2021-05-09 01:23:37', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081380811300866', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/36a15d86-3905-4ddc-aec0-ebeeb5c66fa3.png', 55, '1', '2021-05-09 01:23:41', '1', '2021-05-09 01:23:41', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081392542769153', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/45623130-401a-4a3f-a6fc-db0a507cd7d3.png', 55, '1', '2021-05-09 01:23:44', '1', '2021-05-09 01:23:44', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391081434771021825', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/a4a63f92-3b44-4537-a174-07773c48ed48.png', 55, '1', '2021-05-09 01:23:54', '1', '2021-05-09 01:23:54', NULL, '.png', 'pear-admin', b'0');
INSERT INTO `sys_oss` VALUES ('1391360974042927105', 'pear.png', 'aliyun', 'http://pear-admin.oss-cn-beijing.aliyuncs.com/2988a049-4510-4111-a5be-3d56869bfc40.png', 55, '1', '2021-05-09 19:54:41', '1', '2021-05-09 19:54:41', NULL, '.png', 'pear-admin', b'0');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '岗位标识',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '启用',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1388197937639247873', '部门经理', '123', 0, b'1', '1', '2021-05-01 02:25:54', '1', '2021-07-26 15:38:59', b'0', '岗位描述', '1');
INSERT INTO `sys_post` VALUES ('1388198092773969922', '开发经理', '123', 0, b'1', '1', '2021-05-01 02:26:31', NULL, NULL, b'0', '岗位描述', '1');

-- ----------------------------
-- Table structure for sys_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_power`;
CREATE TABLE `sys_power`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `parent` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父级',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由名称',
  `type` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限类型',
  `icon` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图标',
  `title` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `link` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '外链',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '状态',
  `i18n` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '国际化',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '逻辑删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_power
-- ----------------------------
INSERT INTO `sys_power` VALUES ('1', '0', '/dashboard', 'dashboard', '0', 'HomeOutlined', '工作空间', 0, NULL, b'1', 'dashboard', NULL, NULL, NULL, '1', '2021-07-24 00:09:59', b'0', NULL);
INSERT INTO `sys_power` VALUES ('10', '7', '/system/power', 'power', '1', 'DashboardOutlined', '权限管理', 0, NULL, b'1', 'system-power', NULL, NULL, NULL, '1', '2021-07-22 14:29:07', b'0', NULL);
INSERT INTO `sys_power` VALUES ('11', '18', '/develop/doc', 'doc', '1', 'DashboardOutlined', '接口文档', 1, NULL, b'1', 'monitor-doc', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('12', '18', '/develop/config', 'config', '1', 'DashboardOutlined', '配置管理', 1, NULL, b'1', 'system-config', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('13', '18', '/develop/dict', 'dict', '1', 'DashboardOutlined', '数据字典', 1, NULL, b'1', 'system-dict', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('14', '4', '/monitor/redis', 'redis', '1', 'DashboardOutlined', '缓存监控', 1, NULL, b'1', 'monitor-redis', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('1417039011644137473', '10', NULL, NULL, '2', NULL, '新增权限', 0, NULL, b'1', 'sys-power-save', 'sys:power:save', '1', '2021-07-19 16:30:02', '1', '2021-07-19 16:30:56', b'0', NULL);
INSERT INTO `sys_power` VALUES ('1417039162970431489', '10', NULL, NULL, '2', NULL, '修改权限', 0, NULL, b'1', 'sys-power-edit', 'sys:power:edit', '1', '2021-07-19 16:30:38', '1', '2021-07-19 16:30:38', b'0', NULL);
INSERT INTO `sys_power` VALUES ('1417044703532769282', '10', NULL, NULL, '2', NULL, '查看权限', 0, NULL, b'1', 'sys-power-info', 'sys:power:info', '1', '2021-07-19 16:52:39', '1', '2021-07-19 16:52:39', b'0', NULL);
INSERT INTO `sys_power` VALUES ('1417044875528593409', '10', NULL, NULL, '2', NULL, '删除权限', 0, NULL, b'1', 'sys-power-remove', 'sys:power:remove', '1', '2021-07-19 16:53:20', '1', '2021-07-19 16:53:20', b'0', NULL);
INSERT INTO `sys_power` VALUES ('1421947314073481217', '7', '/system/tenant', 'tenant', '1', NULL, '租户管理', 0, NULL, b'1', 'system-tenant', NULL, '1417321936109387778', '2021-08-02 05:33:53', '1417321936109387778', '2021-08-02 05:58:20', b'0', NULL);
INSERT INTO `sys_power` VALUES ('1427568430800408578', '18', '/develop/sms', 'sms', '1', NULL, '短信发送', 0, NULL, b'1', 'develop-sms', NULL, '1', '2021-08-17 17:50:11', '1', '2021-08-17 17:50:29', b'0', NULL);
INSERT INTO `sys_power` VALUES ('15', '7', '/system/post', 'post', '1', 'DashboardOutlined', '岗位管理', 1, NULL, b'1', 'system-post', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('16', '4', '/monitor/log-oper', 'log-oper', '1', 'DashboardOutlined', '操作日志', 1, NULL, b'1', 'monitor-log-oper', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('17', '4', '/monitor/log-auth', 'log-auth', '1', 'DashboardOutlined', '登录日志', 1, NULL, b'1', 'monitor-log-auth', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('18', '0', '/develop', 'develop', '1', 'DashboardOutlined', '开发工具', 4, NULL, b'1', 'develop', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('19', '18', '/develop/mail', 'mail', '1', 'DashboardOutlined', '发送邮箱', 1, NULL, b'1', 'develop-mail', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('2', '1', '/dashboard/workspace', 'dashboard-workspace', '1', 'DashboardOutlined', '工作台', 1, NULL, b'1', 'dashboard-workspace', NULL, NULL, NULL, '1', '2021-08-23 23:33:49', b'0', NULL);
INSERT INTO `sys_power` VALUES ('20', '7', '/system/dept', 'dept', '1', 'DashboardOutlined', '部门管理', 1, NULL, b'1', 'system-dept', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('23', '18', '/develop/oss', 'oss', '1', 'DashboardOutlined', '文件列表', 1, NULL, b'1', 'develop-oss', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('24', '18', '/develop/dataSource', 'dataSource', '1', 'DashboardOutlined', '多数据源', 1, NULL, b'1', 'develop-source', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('25', '0', '/task', 'task', '0', 'HomeOutlined', '定时任务', 1, NULL, b'1', 'task', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('26', '25', '/task/job', 'job', '1', 'DashboardOutlined', '任务列表', 1, NULL, b'1', 'task-job', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('27', '25', '/task/jobLog', 'jobLog', '1', 'DashboardOutlined', '任务日志', 1, NULL, b'1', 'task-job-log', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('28', '0', '/notice', 'notice', '0', 'HomeOutlined', '站内消息', 1, NULL, b'1', 'notice', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('29', '28', '/notice/announce', 'announce', '1', 'DashboardOutlined', '通知公告', 1, NULL, b'1', 'notice-announce', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('3', '1', '/dashboard/console', 'dashboard-console', '1', 'DashboardOutlined', '分析页', 0, NULL, b'1', 'dashboard-console', NULL, NULL, NULL, '1', '2021-08-23 23:33:57', b'0', NULL);
INSERT INTO `sys_power` VALUES ('30', '28', '/notice/inbox', 'inbox', '1', 'DashboardOutlined', '私人消息', 1, NULL, b'1', 'notice-inbox', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('4', '0', '/monitor', 'monitor', '0', 'HomeOutlined', '系统监控', 2, NULL, b'1', 'monitor', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('5', '4', '/monitor/server', 'server', '1', 'DashboardOutlined', '运行环境', 1, NULL, b'1', 'monitor-server', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('6', '4', '/monitor/online', 'online', '1', 'DashboardOutlined', '在线用户', 1, NULL, b'1', 'monitor-online', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('7', '0', '/system', 'system', '0', 'HomeOutlined', '系统管理', 3, NULL, b'1', 'system', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('8', '7', '/system/user', 'user', '1', 'DashboardOutlined', '用户管理', 1, NULL, b'1', 'system-user', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_power` VALUES ('9', '7', '/system/role', 'role', '1', 'DashboardOutlined', '角色管理', 1, NULL, b'1', 'system-role', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `code` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标识',
  `enable` bit(1) NULL DEFAULT NULL COMMENT '是否开启',
  `sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) NULL DEFAULT NULL COMMENT '是否删除',
  `create_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户编号',
  `scope` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '数据权限 1. 全部权限 2. 自定义权限 3. 部门权限 4. 部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1422936376464842756 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', b'1', '2', '超级管理员描述', NULL, NULL, NULL, NULL, NULL, '1', 'SELF');
INSERT INTO `sys_role` VALUES (2, '普通管理员', 'manage', b'1', '2', '普通管理员描述', b'0', NULL, NULL, '1', '2021-05-05 04:37:32', '1', 'DEPT');
INSERT INTO `sys_role` VALUES (3, '应急管理员', 'user', b'1', '2', '应急管理员描述', NULL, NULL, NULL, NULL, NULL, '2', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dept_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_power
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_power`;
CREATE TABLE `sys_role_power`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `role_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编号',
  `power_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限编号 ',
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_power
-- ----------------------------
INSERT INTO `sys_role_power` VALUES ('1419384372028153857', '3', '1', '1');
INSERT INTO `sys_role_power` VALUES ('1419384372028153858', '3', '2', '1');
INSERT INTO `sys_role_power` VALUES ('1419384372028153859', '3', '3', '1');
INSERT INTO `sys_role_power` VALUES ('1419384372028153860', '3', '28', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640148537345', '2', '1', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640148537346', '2', '28', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925953', '2', '25', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925954', '2', '4', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925955', '2', '7', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925956', '2', '18', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925957', '2', '24', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640156925958', '2', '23', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640165314562', '2', '19', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640165314563', '2', '13', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640165314564', '2', '12', '1');
INSERT INTO `sys_role_power` VALUES ('1420002640165314565', '2', '11', '1');
INSERT INTO `sys_role_power` VALUES ('1427568572748238849', '1', '26', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627457', '1', '1417039162970431489', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627458', '1', '10', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627459', '1', '7', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627460', '1', '1417044875528593409', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627461', '1', '1417044703532769282', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627462', '1', '1417039011644137473', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627463', '1', '1421947314073481217', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627464', '1', '9', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627465', '1', '8', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627466', '1', '20', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627467', '1', '15', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627468', '1', '11', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572756627469', '1', '18', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572765016066', '1', '24', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572765016067', '1', '23', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572765016068', '1', '19', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572765016069', '1', '12', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572765016070', '1', '13', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210370', '1', '14', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210371', '1', '4', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210372', '1', '6', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210373', '1', '5', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210374', '1', '17', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210375', '1', '16', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210376', '1', '3', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210377', '1', '1', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210378', '1', '2', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210379', '1', '28', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210380', '1', '30', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210381', '1', '29', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210382', '1', '27', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210383', '1', '25', NULL);
INSERT INTO `sys_role_power` VALUES ('1427568572769210384', '1', '1427568430800408578', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `user_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户编号',
  `role_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色编号',
  `tenant_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1389665566669352961', '1388857293993066497', '1', '1');
INSERT INTO `sys_user_role` VALUES ('1389665566669352962', '1388857293993066497', '2', '1');
INSERT INTO `sys_user_role` VALUES ('1389665566669352963', '1388857293993066497', '3', '1');
INSERT INTO `sys_user_role` VALUES ('1419905142085435393', '1', '1', '1');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('scheduler', '1391457610156937218', 'DEFAULT', NULL, 'com.pearadmin.pro.common.quartz.QuartzExecute', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E7065617261646D696E2E70726F2E6D6F64756C65732E6A6F622E646F6D61696E2E5379734A6F62A36ACC8C931FF6860200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C0006656E61626C657400134C6A6176612F6C616E672F426F6F6C65616E3B4C0002696471007E00094C00046E616D6571007E00094C0005706172616D71007E000978720033636F6D2E7065617261646D696E2E70726F2E636F6D6D6F6E2E7765622E626173652E646F6D61696E2E42617365446F6D61696EC0E60E5D759AA9080200065A000764656C657465644C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000672656D61726B71007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787000740001317372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E5050A02122838B5B4D07874000CE4BBBBE58AA1E68F8FE8BFB071007E000E7371007E000F770E05000007E5050A02122838B5B4D0787400076661696C75726574000F302F3130202A202A202A202A203F20737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C75657870017400133133393134353736313031353639333732313874000CE5BC82E5B8B8E4BBBBE58AA1707800);
INSERT INTO `qrtz_job_details` VALUES ('scheduler', '1422975874129162242', 'DEFAULT', NULL, 'com.pearadmin.pro.common.quartz.QuartzExecute', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E7065617261646D696E2E70726F2E6D6F64756C65732E6A6F622E646F6D61696E2E5379734A6F62A36ACC8C931FF6860200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C0006656E61626C657400134C6A6176612F6C616E672F426F6F6C65616E3B4C0002696471007E00094C00046E616D6571007E00094C0005706172616D71007E000978720033636F6D2E7065617261646D696E2E70726F2E636F6D6D6F6E2E7765622E626173652E646F6D61696E2E42617365446F6D61696EC0E60E5D759AA9080200065A000764656C657465644C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000672656D61726B71007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787000740001317372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770E05000007E50805012900278C923C7874000CE4BBBBE58AA1E68F8FE8BFB071007E000E7371007E000F770E05000007E50805012900278C923C7874000673696D706C6574000E302F3130202A202A202A202A203F737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C75657870017400133134323239373538373431323931363232343274000CE7AE80E58D95E4BBBBE58AA1707800);

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(0) NULL DEFAULT NULL,
  `PRIORITY` int(0) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `START_TIME` bigint(0) NOT NULL,
  `END_TIME` bigint(0) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(0) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('scheduler', '1391457610156937218', 'DEFAULT', '1391457610156937218', 'DEFAULT', NULL, 1629866750000, 1629866740000, 5, 'ACQUIRED', 'CRON', 1620584320000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E7065617261646D696E2E70726F2E6D6F64756C65732E6A6F622E646F6D61696E2E5379734A6F62A36ACC8C931FF6860200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C0006656E61626C657400134C6A6176612F6C616E672F426F6F6C65616E3B4C0002696471007E00094C00046E616D6571007E00094C0005706172616D71007E000978720033636F6D2E7065617261646D696E2E70726F2E636F6D6D6F6E2E7765622E626173652E646F6D61696E2E42617365446F6D61696EC0E60E5D759AA9080200065A000764656C657465644C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000672656D61726B71007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787000740001317372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E5050A0212D67874000CE4BBBBE58AA1E68F8FE8BFB0740001317371007E000F770A05000007E508021336EB787400076661696C75726574000E302F3130202A202A202A202A203F737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C75657870017400133133393134353736313031353639333732313874000CE5BC82E5B8B8E4BBBBE58AA1707800);
INSERT INTO `qrtz_triggers` VALUES ('scheduler', '1422975874129162242', 'DEFAULT', '1422975874129162242', 'DEFAULT', NULL, 1629866750000, 1629866740000, 5, 'WAITING', 'CRON', 1628098860000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002B636F6D2E7065617261646D696E2E70726F2E6D6F64756C65732E6A6F622E646F6D61696E2E5379734A6F62A36ACC8C931FF6860200064C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C0006656E61626C657400134C6A6176612F6C616E672F426F6F6C65616E3B4C0002696471007E00094C00046E616D6571007E00094C0005706172616D71007E000978720033636F6D2E7065617261646D696E2E70726F2E636F6D6D6F6E2E7765622E626173652E646F6D61696E2E42617365446F6D61696EC0E60E5D759AA9080200065A000764656C657465644C0008637265617465427971007E00094C000A63726561746554696D657400194C6A6176612F74696D652F4C6F63616C4461746554696D653B4C000672656D61726B71007E00094C0008757064617465427971007E00094C000A75706461746554696D6571007E000C787000740001317372000D6A6176612E74696D652E536572955D84BA1B2248B20C00007870770A05000007E508050129FE7874000CE4BBBBE58AA1E68F8FE8BFB0740001317371007E000F770A05000007E508050129FE7874000673696D706C6574000E302F3130202A202A202A202A203F737200116A6176612E6C616E672E426F6F6C65616ECD207280D59CFAEE0200015A000576616C75657870017400133134323239373538373431323931363232343274000CE7AE80E58D95E4BBBBE58AA1707800);

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', '1391457610156937218', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('scheduler', '1422975874129162242', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FIRED_TIME` bigint(0) NOT NULL,
  `SCHED_TIME` bigint(0) NOT NULL,
  `PRIORITY` int(0) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `JOB_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
INSERT INTO `qrtz_fired_triggers` VALUES ('scheduler', 'NON_CLUSTERED1629866704783', '1391457610156937218', 'DEFAULT', 'NON_CLUSTERED', 1629866740043, 1629866750000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('PearScheduler', 'TRIGGER_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `INSTANCE_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(0) NOT NULL,
  `CHECKIN_INTERVAL` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('clusteredScheduler', 'DESKTOP-PIR6OQF1620332980122', 1620334804608, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `REPEAT_COUNT` bigint(0) NOT NULL,
  `REPEAT_INTERVAL` bigint(0) NOT NULL,
  `TIMES_TRIGGERED` bigint(0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_NAME` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TRIGGER_GROUP` varchar(190) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(0) NULL DEFAULT NULL,
  `INT_PROP_2` int(0) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(0) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(0) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;
-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------