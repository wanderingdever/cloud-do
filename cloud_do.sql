/*
 Navicat Premium Dump SQL

 Source Server         : 192.168.5.111-3124
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 192.168.5.111:3124
 Source Schema         : cloud_do

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 25/02/2025 10:50:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_record
-- ----------------------------
DROP TABLE IF EXISTS `file_record`;
CREATE TABLE `file_record`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `file_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件原名',
    `file`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
    `file_type`   char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NOT NULL COMMENT '文件类型',
    `file_md5`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件md5',
    `file_size`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci          DEFAULT NULL COMMENT '大小(字节)',
    `downloads`   int unsigned NOT NULL DEFAULT '0' COMMENT '下载次数',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`         tinyint                                                       NOT NULL DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `index_md5` (`file_md5`) USING BTREE COMMENT '文件md5值'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文件记录表';

-- ----------------------------
-- Records of file_record
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for login_logs
-- ----------------------------
DROP TABLE IF EXISTS `login_logs`;
CREATE TABLE `login_logs` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `login_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录方式',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录IP地址',
  `ip_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '登录地点',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '浏览器类型',
  `login_time` datetime(3) NOT NULL COMMENT '访问时间',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  `del` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='登录记录';

-- ----------------------------
-- Records of login_logs
-- ----------------------------
BEGIN;
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (1, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:30:32.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (2, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:39:13.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (3, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:39:14.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (4, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:55:25.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (5, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:55:43.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (6, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:55:53.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (7, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:56:03.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (8, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:57:09.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (9, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:57:35.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (10, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:57:57.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (11, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 10:58:22.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (12, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 11:35:23.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (13, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 11:40:05.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (14, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-08 11:41:21.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (15, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-10 10:23:07.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (16, 1, 'PWD', '192.168.5.12', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36 Edg/131.0.0.0',
        '2025-02-11 10:56:34.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (17, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36',
        '2025-02-14 10:10:19.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (18, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36',
        '2025-02-14 15:10:52.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (19, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36',
        '2025-02-17 10:29:23.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (20, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',
        '2025-02-20 15:56:40.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (21, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',
        '2025-02-20 16:03:17.000', NULL, NULL, 0);
INSERT INTO `login_logs` (`id`, `user_id`, `login_type`, `ip`, `ip_location`, `browser`, `login_time`, `create_time`,
                          `update_time`, `del`)
VALUES (22, 1, 'PWD', '192.168.5.82', ',,内网IP',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36',
        '2025-02-20 16:06:51.000', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `config_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '参数键值',
  `is_system` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否是系统内置',
  `enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统参数配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_type_id` bigint unsigned NOT NULL COMMENT '字典类型ID easy_dict_type=>id',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典键值',
  `dict_sort` int unsigned NOT NULL DEFAULT '0' COMMENT '字典排序',
  `css_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典数据';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, 1, '启用', 'true', 1, '', 'success', 1, NULL, NULL, NULL, '2024-09-12 11:41:21.820', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (2, 1, '停用', 'false', 2, '', 'danger', 1, NULL, NULL, NULL, '2024-09-12 11:41:23.887', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (3, 2, '是', 'true', 1, '{font-size: 16px;}', 'success', 1, NULL, '2022-11-02 21:44:07.000', '',
        '2024-09-12 11:41:24.853', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (4, 2, '否', 'false', 2, NULL, 'danger', 1, NULL, '2022-11-02 21:44:07.000', NULL, '2024-09-12 11:41:25.619', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (5, 3, '目录', 'CATALOGUE', 1, NULL, 'success', 1, NULL, '2022-11-02 21:44:07.000', NULL,
        '2024-09-12 11:41:26.367', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (6, 3, '按钮', 'BUTTON', 3, NULL, 'info', 1, NULL, '2022-11-02 21:44:07.000', NULL, '2024-09-12 11:41:27.233',
        0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (7, 3, '页面', 'PAGE', 2, NULL, 'success', 1, NULL, '2022-11-02 21:44:07.000', NULL, '2024-09-12 11:41:27.970',
        0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (8, 4, '男', 'MAN', 1, '', 'primary', 1, '1701847090820464641', '2023-11-30 11:25:08.000', NULL,
        '2024-09-12 11:41:28.685', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (9, 4, '女', 'WOMAN', 2, '', 'primary', 1, '1701847090820464641', '2023-11-30 11:25:27.000', NULL,
        '2024-09-12 11:41:29.465', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (10, 4, '未知', 'UNKNOWN', 3, '', 'primary', 1, '1701847090820464641', '2023-11-30 11:26:01.000', '1',
        '2025-02-10 15:16:17.000', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (11, 5, '未激活', 'INACTIVATED', 1, '', 'warning', 1, '1701847090820464641', '2023-11-30 11:31:12.000', NULL,
        '2024-09-12 11:41:33.213', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (12, 5, '正常', 'NORMAL', 2, '', 'success', 1, '1701847090820464641', '2023-11-30 11:31:24.000', NULL,
        '2024-09-12 11:41:33.894', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (13, 5, '停用', 'STOP', 3, '', 'danger', 1, '1701847090820464641', '2023-11-30 11:31:35.000', NULL,
        '2024-09-12 11:41:34.801', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (14, 6, 'WEB', 'WEB', 2, '', 'default', 1, '1701847090820464641', '2023-11-30 13:52:37.000', NULL,
        '2024-10-09 16:38:57.000', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (15, 6, 'APP', 'APP', 3, '', 'default', 1, '1701847090820464641', '2023-11-30 13:52:44.000', NULL,
        '2024-10-09 16:39:00.000', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (16, 6, '管理端', 'SYSTEM', 1, '', 'default', 1, '1701847090820464641', '2023-11-30 13:53:14.000', '1',
        '2025-02-10 15:05:17.000', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (17, 7, '本级', 'ONESELF', 1, '', 'primary', 1, '1701847090820464641', '2023-12-06 09:21:38.000', NULL,
        '2024-09-12 11:41:38.742', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (18, 7, '本级及以下', 'LOWER', 2, '', 'primary', 1, '1701847090820464641', '2023-12-06 09:21:55.000', NULL,
        '2024-09-12 11:41:39.678', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (19, 8, '公告', 'NOTICE', 1, '', 'default', 1, '1701847090820464641', '2023-12-12 16:46:53.000', NULL,
        '2024-09-12 11:41:40.805', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (20, 9, '草稿', 'DRAFT', 1, NULL, 'warning', 1, '1701847090820464641', '2023-12-25 16:54:11.000', NULL,
        '2024-09-12 11:41:41.644', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (21, 9, '已发布', 'UNREAD', 2, NULL, 'primary', 1, '1701847090820464641', '2023-12-25 16:54:34.000', NULL,
        '2024-09-12 11:41:42.452', 0);
INSERT INTO `sys_dict_data` (`id`, `dict_type_id`, `dict_label`, `dict_value`, `dict_sort`, `css_class`, `list_class`,
                             `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (22, 9, '已读', 'READ', 3, NULL, 'success', 1, '1701847090820464641', '2023-12-25 16:54:46.000', NULL,
        '2024-09-12 11:41:43.634', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `dict_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '字典类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典描述',
  `is_system` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否是系统内置',
  `enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_by` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, '启用停用', 'system_enable', '启用或者停用', '1', 1, NULL, NULL, '1701847090820464641',
        '2024-09-12 11:39:40.106', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (2, '是否', 'yes_or_no', '是否', '1', 1, NULL, '2023-09-26 13:58:50.000', '1701847090820464641',
        '2024-09-12 11:39:42.351', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (3, '菜单类型', 'system_menu', '菜单类型', '1', 1, NULL, '2022-11-02 21:44:07.000', NULL,
        '2024-09-12 11:39:43.792', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (4, '性别', 'system_sex', '性别', '1', 1, '1701847090820464641', '2023-11-30 11:24:44.000',
        '1701847090820464641', '2024-09-12 11:40:36.530', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (5, '账号状态', 'system_account_status', '用户账号的状态', '1', 1, '1701847090820464641',
        '2023-11-30 11:30:55.000', NULL, '2024-09-12 11:39:48.204', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (6, '客户端', 'system_client', '所有的账号归属客户端', '1', 1, '1701847090820464641', '2023-11-30 13:52:18.000',
        NULL, '2024-09-12 11:39:49.908', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (7, '数据权限', 'system_authority_level', '角色数据权限', '1', 1, '1701847090820464641',
        '2023-12-06 09:21:17.000', NULL, '2024-09-12 11:39:51.807', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (8, '通知类型', 'system_notification', '通知类型', '1', 1, '1701847090820464641', '2023-12-12 16:45:08.000',
        NULL, '2024-09-12 11:39:53.538', 0);
INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `description`, `is_system`, `enable`, `create_by`,
                             `create_time`, `update_by`, `update_time`, `del`)
VALUES (9, '文章状态', 'system_article_status', '文章状态', '1', 1, '1701847090820464641', '2023-12-25 16:53:25.000',
        '1701847090820464641', '2024-09-12 11:39:55.092', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `menu_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
    `menu_type`     char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci    NOT NULL COMMENT '菜单类型',
    `parent_id`     bigint unsigned NOT NULL DEFAULT '0' COMMENT '父菜单ID',
    `order_num`     int                                                           DEFAULT '0' COMMENT '显示顺序',
    `path`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '路由地址',
    `component`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '组件路径',
    `redirect`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '重定向',
    `is_iframe`     tinyint                                                       DEFAULT '0' COMMENT '是否为外链',
    `is_link`       tinyint                                                       DEFAULT '0' COMMENT '是否为外链',
    `is_affix`      tinyint                                                       DEFAULT '0' COMMENT '是否固定',
    `is_keep_alive` tinyint                                                       DEFAULT '0' COMMENT '是否缓存',
    `is_hide`       tinyint                                                       DEFAULT '0' COMMENT '菜单是否显示',
    `link`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
    `perms`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '权限标识',
    `icon`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT '#' COMMENT '菜单图标',
    `enable`        tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
    `create_by`     char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '创建人',
    `create_time`   datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`     char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '更新人',
    `update_time`   datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`           tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限信息';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, '首页', 'PAGE', 0, 1, 'home', 'home/index', '', 0, 0, 0, 1, 0, NULL, 'home', 'iconfont icon-shouye', 1, NULL,
        '2024-10-09 11:00:53.000', NULL, '2024-10-12 10:23:12.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (2, '系统管理', 'CATALOGUE', 0, 13, 'system', NULL, '/system/menu', 0, 0, 0, 0, 0, '0', 'system.menu',
        'iconfont icon-xitongshezhi', 1, NULL, '2022-11-18 19:27:43.000', NULL, '2024-10-10 09:58:37.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (3, '菜单配置', 'PAGE', 2, 1, 'system/menu', 'system/menu/index', NULL, 0, 0, 0, 1, 0, '0', 'system.menu.tree',
        'iconfont icon-caidan', 1, NULL, '2022-11-05 00:20:58.000', NULL, '2024-09-29 14:26:18.058', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (4, '参数配置', 'PAGE', 2, 7, 'system/config', 'system/config/index', NULL, 0, 0, 0, 1, 0, '0',
        'system.config.page', 'iconfont icon-xitongshezhi', 1, NULL, '2022-12-22 00:09:14.000', NULL,
        '2024-09-12 11:42:51.152', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (5, '字典管理', 'PAGE', 2, 6, 'system/dict', 'system/dict/index', NULL, 0, 0, 0, 1, 0, '0', 'system.dict.page',
        'iconfont icon-diannao1', 1, NULL, '2022-12-21 00:10:37.000', NULL, '2024-09-12 11:42:58.112', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (6, '机构管理', 'PAGE', 2, 2, 'system/org', 'system/org/index', '', 0, 0, 0, 1, 0, '', 'system.org.tree',
        'iconfont icon-shuxingtu', 1, NULL, '2023-11-28 16:03:17.000', NULL, '2024-09-12 11:42:58.116', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (7, '用户管理', 'PAGE', 2, 3, 'system/user', 'system/user/index', '', 0, 0, 0, 1, 0, '', 'system.user.page',
        'iconfont icon-icon-', 1, NULL, '2023-11-30 09:49:51.000', NULL, '2024-09-12 11:42:51.172', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (8, '角色管理', 'PAGE', 2, 4, 'system/role', 'system/role/index', '', 0, 0, 0, 1, 0, '', 'system.role.page',
        'iconfont icon-gerenzhongxin', 1, NULL, '2023-12-04 16:11:48.000', NULL, '2024-09-12 11:42:58.125', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (9, '新增', 'BUTTON', 3, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.menu.add', '', 1, NULL,
        '2023-12-08 11:19:07.000', NULL, '2024-09-12 13:49:40.561', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (10, '通知消息', 'PAGE', 2, 8, 'system/msg', 'system/msg/index', '', 0, 0, 0, 1, 0, '', 'system.msg.page',
        'iconfont icon-tongzhi1', 1, NULL, '2023-12-11 14:05:27.000', NULL, '2024-09-12 13:49:40.568', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (11, '编辑', 'BUTTON', 3, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.menu.update', '', 1, NULL,
        '2023-12-12 16:18:13.000', NULL, '2024-09-12 13:49:40.575', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (12, '删除', 'BUTTON', 3, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.menu.del', '', 1, NULL,
        '2023-12-12 16:18:31.000', NULL, '2024-09-12 13:49:40.582', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (13, '新增', 'BUTTON', 5, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.dict.add', '', 1, NULL,
        '2023-12-12 16:19:30.000', NULL, '2024-09-12 13:49:40.588', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (14, '编辑', 'BUTTON', 5, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.dict.update', '', 1, NULL,
        '2023-12-12 16:19:41.000', NULL, '2024-09-12 13:49:40.594', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (15, '删除', 'BUTTON', 5, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.dict.del', '', 1, NULL,
        '2023-12-12 16:19:55.000', NULL, '2024-09-12 13:49:40.601', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (16, '删除', 'BUTTON', 4, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.config.del', '', 1, NULL,
        '2023-12-12 16:23:05.000', NULL, '2024-09-12 13:49:40.607', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (17, '新增', 'BUTTON', 4, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.config.add', '', 1, NULL,
        '2023-12-12 16:23:21.000', NULL, '2024-09-12 13:49:40.614', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (18, '编辑', 'BUTTON', 4, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.config.update', '', 1, NULL,
        '2023-12-12 16:23:35.000', NULL, '2024-09-12 13:49:40.621', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (19, '新增', 'BUTTON', 8, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.role.add', '', 1, NULL,
        '2023-12-12 16:39:25.000', NULL, '2024-09-12 13:49:40.626', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (20, '编辑', 'BUTTON', 8, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.role.update', '', 1, NULL,
        '2023-12-12 16:39:37.000', NULL, '2024-09-12 13:49:40.631', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (21, '删除', 'BUTTON', 8, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.role.del', '', 1, NULL,
        '2023-12-12 16:39:47.000', NULL, '2024-09-12 13:49:40.637', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (22, '删除', 'BUTTON', 7, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.user.del', '', 1, NULL,
        '2023-12-12 16:40:03.000', NULL, '2024-09-12 13:49:40.642', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (23, '编辑', 'BUTTON', 7, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.user.update', '', 1, NULL,
        '2023-12-12 16:40:14.000', NULL, '2024-09-12 13:49:40.646', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (24, '新增', 'BUTTON', 7, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.user.add', '', 1, NULL,
        '2023-12-12 16:40:23.000', NULL, '2024-09-12 13:49:40.651', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (25, '新增', 'BUTTON', 6, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.org.add', '', 1, NULL,
        '2023-12-12 16:40:52.000', NULL, '2024-09-12 13:49:40.657', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (26, '删除', 'BUTTON', 6, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.org.del', '', 1, NULL,
        '2023-12-12 16:41:47.000', NULL, '2024-09-12 13:49:40.663', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (27, '编辑', 'BUTTON', 6, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.org.update', '', 1, NULL,
        '2023-12-12 16:42:02.000', NULL, '2024-09-12 13:49:40.667', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (28, '编辑', 'BUTTON', 10, 2, '', '', '', 0, 0, 0, 1, 0, '', 'system.msg.update', '', 1, NULL,
        '2023-12-12 16:43:41.000', NULL, '2024-10-12 10:22:14.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (29, '删除', 'BUTTON', 10, 3, '', '', '', 0, 0, 0, 1, 0, '', 'system.msg.del', '', 1, NULL,
        '2023-12-12 16:43:55.000', NULL, '2024-10-12 10:22:20.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (30, '新增', 'BUTTON', 10, 1, '', '', '', 0, 0, 0, 1, 0, '', 'system.msg.add', '', 1, NULL,
        '2023-12-12 16:44:04.000', NULL, '2024-10-12 10:22:07.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (38, '文件管理', 'PAGE', 2, 9, 'system/file', 'system/file/index', '', 0, 0, 0, 1, 0, NULL, 'system.file.page',
        'iconfont icon-zhongduancanshuchaxun', 1, NULL, '2024-10-10 11:41:10.000', NULL, '2024-10-12 10:22:54.000', 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (39, '知识库', 'CATALOGUE', 0, 1, 'dataset', '', '', 0, 0, 0, 1, 0, NULL, 'dataset',
        'iconfont icon-zhongduancanshuchaxun', 1, '1', '2025-02-14 10:51:22.000', NULL, NULL, 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (40, '知识库列表', 'PAGE', 39, 1, 'dataset/index', 'dataset/index', '', 0, 0, 0, 1, 0, NULL, 'dataset.index', '',
        1, '1', '2025-02-14 10:52:15.000', NULL, NULL, 0);
INSERT INTO `sys_menu` (`id`, `menu_name`, `menu_type`, `parent_id`, `order_num`, `path`, `component`, `redirect`,
                        `is_iframe`, `is_link`, `is_affix`, `is_keep_alive`, `is_hide`, `link`, `perms`, `icon`,
                        `enable`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (41, '知识库详情', 'PAGE', 39, 1, 'dataset/detail/:knowledgeBaseId', 'dataset/detail', '', 0, 0, 0, 1, 1, NULL,
        'dataset.detail', '', 1, '1', '2025-02-20 16:16:09.000', '1', '2025-02-20 17:03:17.000', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`
(
    `id`             bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `org_parent_id`  bigint unsigned NOT NULL DEFAULT '0' COMMENT '上级id',
    `org_name`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织名称',
    `org_short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '组织简称',
    `org_level`      char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '组织级别',
    `org_type`       char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '组织类型',
    `org_nature`     char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '组织性质',
    `org_code`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构编码',
    `org_tag`        char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '组织标签',
    `org_province`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '省',
    `org_city`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '市',
    `org_district`   varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '区',
    `org_area_code`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '区划代码集',
    `org_address`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细地址',
    `org_desc`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构简介',
    `org_logo`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'logo',
    `org_sort`       int                                                           DEFAULT NULL COMMENT '顺序',
    `org_status`     char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      DEFAULT NULL COMMENT '组织状态',
    `create_by`      char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '创建人',
    `create_time`    datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`      char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     DEFAULT NULL COMMENT '更新人',
    `update_time`    datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`            tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织信息';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_org` (`id`, `org_parent_id`, `org_name`, `org_short_name`, `org_level`, `org_type`, `org_nature`,
                       `org_code`, `org_tag`, `org_province`, `org_city`, `org_district`, `org_area_code`,
                       `org_address`, `org_desc`, `org_logo`, `org_sort`, `org_status`, `create_by`, `create_time`,
                       `update_by`, `update_time`, `del`)
VALUES (1, 0, '测试机构', '测试 1', '一级', '测试', '测试', '001', '测试', '110000', '110100', '110101',
        '110000,110100,110101', '测试地址', '测试简介', '', 1, NULL, NULL, '2023-11-29 10:58:17.000', '1',
        '2025-02-10 16:10:35.000', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`              bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `org_id`          bigint unsigned NOT NULL COMMENT '归属组织ID',
    `role_name`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '角色名字',
    `role_key`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色key',
    `authority_level` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '权限级别',
    `role_sort`       int unsigned NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `enable`          tinyint                                                       NOT NULL DEFAULT '1' COMMENT '是否启用',
    `create_by`       char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci              DEFAULT NULL COMMENT '创建人',
    `create_time`     datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`       char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci              DEFAULT NULL COMMENT '更新人',
    `update_time`     datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`             tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色信息';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `org_id`, `role_name`, `role_key`, `authority_level`, `role_sort`, `enable`, `create_by`,
                        `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, 1, '超级管理员', 'admin', 'ONESELF', 1, 1, NULL, NULL, '1', '2025-02-11 11:51:03.000', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `menu_id`     bigint unsigned NOT NULL COMMENT '菜单ID',
    `create_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`         tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色->菜单权限关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (117, 1, 1, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (118, 1, 2, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (119, 1, 3, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (120, 1, 9, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (121, 1, 11, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (122, 1, 12, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (123, 1, 6, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (124, 1, 25, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (125, 1, 27, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (126, 1, 26, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (127, 1, 7, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (128, 1, 24, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (129, 1, 23, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (130, 1, 22, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (131, 1, 8, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (132, 1, 19, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (133, 1, 20, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (134, 1, 21, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (135, 1, 5, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (136, 1, 13, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (137, 1, 30, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (138, 1, 28, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (139, 1, 29, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (140, 1, 14, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (141, 1, 15, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (142, 1, 4, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (143, 1, 17, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (144, 1, 18, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (145, 1, 16, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (146, 1, 10, NULL, '2024-10-09 16:51:57.000', NULL, '2025-02-11 11:51:03.478', 1);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (147, 1, 2, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (148, 1, 1, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (149, 1, 3, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (150, 1, 9, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (151, 1, 11, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (152, 1, 12, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (153, 1, 6, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (154, 1, 25, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (155, 1, 27, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (156, 1, 26, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (157, 1, 7, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (158, 1, 24, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (159, 1, 23, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (160, 1, 22, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (161, 1, 8, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (162, 1, 19, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (163, 1, 20, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (164, 1, 21, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (165, 1, 5, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (166, 1, 13, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (167, 1, 14, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (168, 1, 15, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (169, 1, 4, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (170, 1, 17, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (171, 1, 18, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (172, 1, 16, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (173, 1, 10, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (174, 1, 30, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (175, 1, 28, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (176, 1, 29, '1', '2025-02-11 11:51:03.000', NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户id',
    `org_id`      bigint unsigned NOT NULL COMMENT '机构id',
    `create_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`         tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户->机构关联';

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_org` (`id`, `user_id`, `org_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, 1, 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     bigint unsigned NOT NULL COMMENT '用户ID',
    `role_id`     bigint unsigned NOT NULL COMMENT '角色ID',
    `create_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
    `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
    `update_by`   char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
    `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    `del`         tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户->角色关联';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `create_by`, `create_time`, `update_by`, `update_time`, `del`)
VALUES (1, 1, 1, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` char(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `client` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属客户端',
  `status` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号状态',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `del` int unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_username_del` (`username`,`del`) USING BTREE COMMENT '用户名+逻辑删除唯一',
  UNIQUE KEY `index_email_del` (`email`,`del`) USING BTREE COMMENT '邮箱+逻辑删除唯一',
  UNIQUE KEY `index_phone_del` (`phone`,`del`) USING BTREE COMMENT '手机号+逻辑删除唯一'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='账号信息';

-- ----------------------------
-- Records of user_account
-- ----------------------------
BEGIN;
INSERT INTO `user_account` (`id`, `username`, `email`, `phone`, `password`, `client`, `status`, `create_time`,
                            `update_time`, `del`)
VALUES (1, 'admin', NULL, NULL, '$2a$10$ctagQJswnSwUyl/akFuFFeLyRO/ffMxuK2HhnzM29w9lfBLoZCU82', 'SYSTEM', 'NORMAL',
        '2025-02-07 15:39:54.000', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户信息ID',
  `client` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '归属客户端',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账号昵称',
  `avatar` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '头像',
  `sex` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
  `del` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除标记',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户基本信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` (`id`, `user_id`, `client`, `nickname`, `avatar`, `sex`, `create_time`, `update_time`, `del`)
VALUES (1, 1, 'SYSTEM', '超级管理员', NULL, '2', '2025-02-07 15:41:07.000', '2025-02-10 10:24:37.291', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;