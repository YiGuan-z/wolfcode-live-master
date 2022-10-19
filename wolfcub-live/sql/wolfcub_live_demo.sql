/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : 49.232.150.194:3306
 Source Schema         : wolfcub_live_demo

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 18/10/2022 17:05:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logging
-- ----------------------------
DROP TABLE IF EXISTS `logging`;
CREATE TABLE `logging` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL COMMENT '用户id用于判断用户状态',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志等级',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '日志创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of logging
-- ----------------------------
BEGIN;
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (1, 1, 'info', '\"admin\"用户已登陆', '2022-10-18 12:48:52');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (2, 1, 'info', '\"admin\"用户已登陆', '2022-10-18 12:51:02');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (3, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 12:52:30');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (4, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 12:54:05');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (5, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 13:55:11');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (6, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 14:17:22');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (7, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 14:25:05');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (8, 24, 'info', '\"root\"用户已在http://localhost:8080/login登陆', '2022-10-18 14:34:59');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (9, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 14:41:03');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (10, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:23:46');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (11, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:25:17');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (12, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:26:51');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (13, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:29:29');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (14, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:31:44');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (15, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:33:54');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (16, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:35:24');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (17, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:35:51');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (18, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:38:58');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (19, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:56:36');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (20, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 15:59:58');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (21, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:01:49');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (22, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:05:19');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (23, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:21:03');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (24, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:26:36');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (25, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:28:08');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (26, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:31:47');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (27, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:36:30');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (28, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 16:53:27');
INSERT INTO `logging` (`id`, `user_id`, `level`, `msg`, `time`) VALUES (29, 1, 'info', '\"admin\"用户已在http://localhost:8080/login登陆', '2022-10-18 17:04:24');
COMMIT;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
BEGIN;
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (1, '四川狼码教育', 'wolfcode', NULL);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (2, '总经办', 'ZJB', 1);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (3, '产品部', 'CPB', 1);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (4, '销售部', 'XSB', 1);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (5, '销售1组', 'XS1', 4);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (6, '销售2组', 'XS2', 4);
INSERT INTO `sys_department` (`id`, `name`, `sn`, `parent_id`) VALUES (7, 'xxxxxx', 'sss', 6);
COMMIT;

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint DEFAULT NULL,
  `hireDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_employee
-- ----------------------------
BEGIN;
INSERT INTO `sys_employee` (`id`, `avatar`, `username`, `name`, `password`, `email`, `age`, `admin`, `dept_id`, `hireDate`) VALUES (1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'admin', '超级管理员221456', '111111', 'root@wolfcode.cn', 18, b'1', 1, '2022-02-15 00:00:00');
INSERT INTO `sys_employee` (`id`, `avatar`, `username`, `name`, `password`, `email`, `age`, `admin`, `dept_id`, `hireDate`) VALUES (21, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'xiaoliu', '小刘', '111111', 'xiaoliu@wolfcode.cn', 18, b'0', 3, '2022-10-13 00:00:00');
INSERT INTO `sys_employee` (`id`, `avatar`, `username`, `name`, `password`, `email`, `age`, `admin`, `dept_id`, `hireDate`) VALUES (22, 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', 'xiaoli', '小李', '111111', 'xiaoli@wolfcode.cn', 17, b'0', 5, '2022-10-05 00:00:00');
INSERT INTO `sys_employee` (`id`, `avatar`, `username`, `name`, `password`, `email`, `age`, `admin`, `dept_id`, `hireDate`) VALUES (23, NULL, 'aaa', 'aa', '12321312', 'da', 32, NULL, 1, NULL);
INSERT INTO `sys_employee` (`id`, `avatar`, `username`, `name`, `password`, `email`, `age`, `admin`, `dept_id`, `hireDate`) VALUES (24, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'root', 'root', '111111', 'ewrasdf', 12, b'1', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_bullet_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_bullet_msg`;
CREATE TABLE `t_bullet_msg` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `author_id` bigint DEFAULT NULL COMMENT '发布人id',
  `video_id` bigint DEFAULT NULL COMMENT '视频id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '弹幕内容',
  `color` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '弹幕颜色',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '弹幕位置',
  `video_time` int DEFAULT NULL COMMENT '视频时间(秒)',
  `likes` int DEFAULT NULL COMMENT '点赞数',
  `font_size` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字体大小',
  `status` int DEFAULT NULL COMMENT '状态(0=正常, 1=禁用)',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_bullet_msg
-- ----------------------------
BEGIN;
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (1, 1, 1294384753222123, '第一第一', '#FF0000', '0', 1, 0, '12px', 0, '2022-10-14 22:58:45');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (2, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, 0, '2022-10-16 00:01:36');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (3, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, NULL, '2022-10-16 00:01:38');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (4, 1, 1294384753222123, '我们都是追梦人', '#FFFFFF', '0', 1, NULL, NULL, NULL, '2022-10-16 00:01:40');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (5, 1, 1294384753222123, 'hhhhh', '#fff', '0', 7, 0, NULL, 0, '2022-10-16 00:11:26');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (6, 1, 1294384753222123, '66666666', '#fff', '0', 14, 0, NULL, 0, '2022-10-16 00:52:52');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (7, 1, 1294384753222123, '这就有点绿了...', '#64DD17', '0', 38, 0, NULL, 0, '2022-10-16 00:53:16');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (8, 1, 1294384753222123, '看的到吗？', '#D500F9', '0', 96, 0, NULL, 0, '2022-10-18 00:54:28');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (9, 1, 1294384753222123, '可以可以', '#ffe133', '0', 134, 0, NULL, 0, '2022-10-18 00:55:25');
INSERT INTO `t_bullet_msg` (`id`, `author_id`, `video_id`, `content`, `color`, `position`, `video_time`, `likes`, `font_size`, `status`, `release_time`) VALUES (10, 1, 3213123, 'dfafasdf', NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-17 18:57:08');
COMMIT;

-- ----------------------------
-- Table structure for t_bullet_msg_sensitive
-- ----------------------------
DROP TABLE IF EXISTS `t_bullet_msg_sensitive`;
CREATE TABLE `t_bullet_msg_sensitive` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sensitive_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '敏感词',
  `show_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '展示词',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=COMPACT COMMENT='敏感词汇过滤和转义表';

-- ----------------------------
-- Records of t_bullet_msg_sensitive
-- ----------------------------
BEGIN;
INSERT INTO `t_bullet_msg_sensitive` (`id`, `sensitive_msg`, `show_msg`, `create_time`, `update_time`) VALUES (1, 'dasfasd', 'fsad', NULL, NULL);
INSERT INTO `t_bullet_msg_sensitive` (`id`, `sensitive_msg`, `show_msg`, `create_time`, `update_time`) VALUES (2, 'dfadf', 'dasf', '2022-10-18 15:35:38', NULL);
INSERT INTO `t_bullet_msg_sensitive` (`id`, `sensitive_msg`, `show_msg`, `create_time`, `update_time`) VALUES (3, 'dsafsa', 'ewrawdfd', '2022-10-18 15:36:21', '2022-10-18 15:39:11');
COMMIT;

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '举报用户',
  `bullet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联的弹幕Id',
  `report_day` datetime(6) DEFAULT NULL COMMENT '举报时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '举报内容',
  `status` int DEFAULT NULL COMMENT '状态(0已提交,1正在审核,2已提交)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_report
-- ----------------------------
BEGIN;
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (1, '1', '1', '2022-06-03 23:40:32.000000', '3xvtN1QTSk', 1);
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (2, '1', '1', '2013-04-27 16:47:47.000000', 'itb8iHby5h', 0);
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (3, '1', '1', '2015-11-01 11:31:19.000000', 'sgG3wG1fP5', 1);
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (4, '1', '1', '2013-01-28 00:06:53.000000', '8nEREuNRog', 0);
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (5, '1', '1', '2005-11-27 17:17:26.000000', 'szVzisJ1on', 1);
INSERT INTO `t_report` (`id`, `user_id`, `bullet_id`, `report_day`, `content`, `status`) VALUES (6, '1', '1', '2019-06-02 10:05:38.000000', 'dGYy8wsCqv', 0);
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `gender` int DEFAULT NULL COMMENT '性别(0=保密, 1=男, 2=女)',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '签名',
  `status` int DEFAULT NULL COMMENT '状态(0=正常, 1=禁用)',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除(0=正常, 1=删除)',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `username`, `password`, `avatar`, `nickname`, `gender`, `signature`, `status`, `deleted`, `created_time`, `updated_time`) VALUES (1, 'xiaoliu', '111111', '/upload/e14d86ad-94fa-43f4-b180-52fa7a5be9c4.jpg', '小刘', 1, '何以解忧，唯有暴富', 0, 0, '2022-10-14 20:50:38', NULL);
INSERT INTO `t_user` (`id`, `username`, `password`, `avatar`, `nickname`, `gender`, `signature`, `status`, `deleted`, `created_time`, `updated_time`) VALUES (2, 'laoliu', '111111', '/upload/fc55e65c-fc48-43d2-a978-ec45c7155f79.jpeg', '老刘', 0, '哈哈哈哈', 0, 0, '2022-10-18 22:14:58', NULL);
INSERT INTO `t_user` (`id`, `username`, `password`, `avatar`, `nickname`, `gender`, `signature`, `status`, `deleted`, `created_time`, `updated_time`) VALUES (3, 'root', '1', NULL, 'superman', 1, '啊发发', 0, 0, '2022-10-18 08:57:25', NULL);
INSERT INTO `t_user` (`id`, `username`, `password`, `avatar`, `nickname`, `gender`, `signature`, `status`, `deleted`, `created_time`, `updated_time`) VALUES (4, '4srs', '2', NULL, NULL, NULL, NULL, NULL, NULL, '2022-10-18 09:28:48', NULL);
COMMIT;

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '子标题',
  `author_id` bigint DEFAULT NULL COMMENT '作者',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `cover_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '封面',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '视频地址',
  `likes` int DEFAULT NULL COMMENT '点赞数',
  `coins` int DEFAULT NULL COMMENT '投币数',
  `shares` int DEFAULT NULL COMMENT '分享数',
  `comments` int DEFAULT NULL COMMENT '评论数',
  `views` int DEFAULT NULL COMMENT '观看数',
  `status` int DEFAULT NULL COMMENT '视频状态(0=未发布, 1=已发布, 2=已禁用)',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除状态(0=正常, 1=已删除)',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1294384753222124 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_video
-- ----------------------------
BEGIN;
INSERT INTO `t_video` (`id`, `title`, `sub_title`, `author_id`, `description`, `cover_url`, `video_url`, `likes`, `coins`, `shares`, `comments`, `views`, `status`, `release_time`, `deleted`, `created_time`, `updated_time`) VALUES (1294384753222123, '叩丁狼：高性能弹幕系统', '最终版弹幕系统效果...', 1, '弹幕系统', '/images/cover_url.jpg', 'https://blz-videos.nosdn.127.net/1/OverWatch/AnimatedShots/Overwatch_AnimatedShot_Winston_Recall.mp4', 129450, 293757, 28470, 20844, 10374598, 1, '2022-10-14 11:14:40', 0, '2022-10-13 10:14:42', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
