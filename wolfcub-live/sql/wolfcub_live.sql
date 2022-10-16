/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50532
 Source Host           : localhost:3306
 Source Schema         : wolfcub_live

 Target Server Type    : MySQL
 Target Server Version : 50532
 File Encoding         : 65001

 Date: 16/10/2022 01:02:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (1, '四川狼码教育', 'wolfcode', NULL);
INSERT INTO `sys_department` VALUES (2, '总经办', 'ZJB', 1);
INSERT INTO `sys_department` VALUES (3, '产品部', 'CPB', 1);
INSERT INTO `sys_department` VALUES (4, '销售部', 'XSB', 1);
INSERT INTO `sys_department` VALUES (5, '销售1组', 'XS1', 4);
INSERT INTO `sys_department` VALUES (6, '销售2组', 'XS2', 4);
INSERT INTO `sys_department` VALUES (7, 'xxxxxx', 'sss', 6);

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `dept_id` bigint(20) NULL DEFAULT NULL,
  `hireDate` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_employee
-- ----------------------------
INSERT INTO `sys_employee` VALUES (1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'admin', '超级管理员', '111111', 'root@wolfcode.cn', 18, b'1', 1, '2022-02-15 00:00:00');
INSERT INTO `sys_employee` VALUES (21, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'xiaoliu', '小刘', '111111', 'xiaoliu@wolfcode.cn', 18, b'0', 3, '2022-10-13 00:00:00');
INSERT INTO `sys_employee` VALUES (22, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'xiaoli', '小李', '111111', 'xiaoli@wolfcode.cn', 17, b'0', 5, '2022-10-05 00:00:00');

-- ----------------------------
-- Table structure for t_bullet_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_bullet_msg`;
CREATE TABLE `t_bullet_msg`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `author_id` bigint(10) NULL DEFAULT NULL COMMENT '发布人id',
  `video_id` bigint(20) NULL DEFAULT NULL COMMENT '视频id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '弹幕内容',
  `color` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '弹幕颜色',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '弹幕位置',
  `video_time` int(10) NULL DEFAULT NULL COMMENT '视频时间(秒)',
  `likes` int(4) NULL DEFAULT NULL COMMENT '点赞数',
  `font_size` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字体大小',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态(0=正常, 1=禁用)',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_bullet_msg
-- ----------------------------
INSERT INTO `t_bullet_msg` VALUES (1, 1, 1294384753222123, '第一第一', '#FF0000', '0', 1, 0, '12px', 0, '2022-10-14 22:58:45');
INSERT INTO `t_bullet_msg` VALUES (2, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, NULL, '2022-10-16 00:01:36');
INSERT INTO `t_bullet_msg` VALUES (3, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, NULL, '2022-10-16 00:01:38');
INSERT INTO `t_bullet_msg` VALUES (4, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, NULL, '2022-10-16 00:01:40');
INSERT INTO `t_bullet_msg` VALUES (5, 1, 1294384753222123, 'hhhhh', '#fff', '0', 7, 0, NULL, 0, '2022-10-16 00:11:26');
INSERT INTO `t_bullet_msg` VALUES (6, 1, 1294384753222123, '66666666', '#fff', '0', 14, 0, NULL, 0, '2022-10-16 00:52:52');
INSERT INTO `t_bullet_msg` VALUES (7, 1, 1294384753222123, '这就有点绿了...', '#64DD17', '0', 38, 0, NULL, 0, '2022-10-16 00:53:16');
INSERT INTO `t_bullet_msg` VALUES (8, 1, 1294384753222123, '看的到吗？', '#D500F9', '0', 96, 0, NULL, 0, '2022-10-16 00:54:28');
INSERT INTO `t_bullet_msg` VALUES (9, 1, 1294384753222123, '可以可以', '#ffe133', '0', 134, 0, NULL, 0, '2022-10-16 00:55:25');

-- ----------------------------
-- Table structure for t_bullet_msg_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `t_bullet_msg_sensitive`;
CREATE TABLE `t_bullet_msg_sensitive`  (
  `id` bigint(19) UNSIGNED NOT NULL COMMENT '主键',
  `sensitive_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '敏感词',
  `show_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '展示词',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '敏感词汇过滤和转义表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `gender` int(4) NULL DEFAULT NULL COMMENT '性别(0=保密, 1=男, 2=女)',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `status` int(4) NULL DEFAULT NULL COMMENT '状态(0=正常, 1=禁用)',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除(0=正常, 1=删除)',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'xiaoliu', '111111', '/images/a.png', '小刘', 1, '何以解忧，唯有暴富', 0, 0, '2022-10-14 20:50:38', NULL);
INSERT INTO `t_user` VALUES (2, 'laoliu', '111111', '/images/b.png', '老刘', 0, '哈哈哈哈', 0, 0, '2022-10-14 22:14:58', NULL);

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `author_id` bigint(10) NULL DEFAULT NULL COMMENT '作者',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `cover_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频地址',
  `likes` int(8) NULL DEFAULT NULL COMMENT '点赞数',
  `coins` int(8) NULL DEFAULT NULL COMMENT '投币数',
  `shares` int(8) NULL DEFAULT NULL COMMENT '分享数',
  `comments` int(8) NULL DEFAULT NULL COMMENT '评论数',
  `views` int(8) NULL DEFAULT NULL COMMENT '观看数',
  `status` int(4) NULL DEFAULT NULL COMMENT '视频状态(0=未发布, 1=已发布, 2=已禁用)',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态(0=正常, 1=已删除)',
  `created_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES (1294384753222123, '叩丁狼：高性能弹幕系统', '最终版弹幕系统效果...', 1, '弹幕系统', '/images/cover_url.jpg', 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4', 129450, 293757, 28470, 20844, 10374598, 1, '2022-10-14 11:14:40', 0, '2022-10-13 10:14:42', NULL);

SET FOREIGN_KEY_CHECKS = 1;
