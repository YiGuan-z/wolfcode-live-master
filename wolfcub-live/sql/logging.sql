/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : wolfcub_live_demo

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 18/10/2022 16:53:41
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

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
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
