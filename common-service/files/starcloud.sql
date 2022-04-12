/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : starcloud

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 12/04/2022 11:39:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource_file
-- ----------------------------
DROP TABLE IF EXISTS `resource_file`;
CREATE TABLE `resource_file`  (
  `id` bigint(10) UNSIGNED NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `module` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `object_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_file
-- ----------------------------
INSERT INTO `resource_file` VALUES (14384192297973762, '4533.jpg', 'example', 'aaaaa', '2022-03-06 15:09:21', 1);
INSERT INTO `resource_file` VALUES (1438418885537292290, '1233.jpg', 'example', 'bbbb', '2021-09-16 16:29:03', 1);
INSERT INTO `resource_file` VALUES (1438419015623630850, '123.jpg', 'example', 'aaaa', '2021-09-16 16:26:32', 1);
INSERT INTO `resource_file` VALUES (1438419203771719681, '1233.jpg', 'example', 'bbbb', '2021-09-16 16:27:17', 1);
INSERT INTO `resource_file` VALUES (1438419229797376002, '1233.jpg', 'example', 'adsfaf', '2022-03-05 15:25:56', 1);
INSERT INTO `resource_file` VALUES (1438419229797376102, '4533.jpg', 'example', 'aaaaa', '2022-03-06 15:05:57', 1);
INSERT INTO `resource_file` VALUES (1438419229797376112, '4533.jpg', 'example', 'aaaaa', '2022-03-06 15:08:11', 1);
INSERT INTO `resource_file` VALUES (1438419229797376132, '4533.jpg', 'example', 'aaaaa', '2022-03-06 15:08:48', 1);
INSERT INTO `resource_file` VALUES (1438750313227583490, '555.jpg', 'example', 'ggg', '2021-09-17 14:23:00', 1);
INSERT INTO `resource_file` VALUES (1438750589657382913, '555.jpg', 'example', 'ggg', '2021-09-17 14:24:05', 1);
INSERT INTO `resource_file` VALUES (1438750961025253377, '555.jpg', 'example', 'ggg', '2021-09-17 14:25:34', 1);
INSERT INTO `resource_file` VALUES (1438751210330488833, '555.jpg', 'example', 'ggg', '2021-09-17 14:26:33', 1);
INSERT INTO `resource_file` VALUES (1438751509090762754, '555.jpg', 'example', 'ggg', '2021-09-17 14:27:45', 1);
INSERT INTO `resource_file` VALUES (1438751831049732097, '555.jpg', 'example', 'ggg', '2021-09-17 14:29:01', 1);
INSERT INTO `resource_file` VALUES (1438752238618640385, '555.jpg', 'example', 'ggg', '2021-09-17 14:30:39', 1);
INSERT INTO `resource_file` VALUES (1438753646432583682, '555.jpg', 'example', 'ggg', '2021-09-17 14:36:14', 1);
INSERT INTO `resource_file` VALUES (1438755938112208897, '555.jpg', 'example', 'ggg', '2021-09-17 14:45:21', 1);
INSERT INTO `resource_file` VALUES (1438756205423591426, '555.jpg', 'example', 'ggg', '2021-09-17 14:46:24', 1);
INSERT INTO `resource_file` VALUES (1438756548131782658, '555.jpg', 'example', 'ggg', '2021-09-17 14:47:46', 1);
INSERT INTO `resource_file` VALUES (1438757031684702209, '555.jpg', 'example', 'ggg', '2021-09-17 14:49:41', 1);
INSERT INTO `resource_file` VALUES (1438762001658118146, '555.jpg', 'example', 'ggg', '2021-09-17 15:09:26', 1);
INSERT INTO `resource_file` VALUES (1438763085982171138, '555.jpg', 'example', 'ggg', '2021-09-17 15:13:45', 1);
INSERT INTO `resource_file` VALUES (1438763247660007426, '555.jpg', 'example', 'ggg', '2021-09-17 15:14:23', 1);
INSERT INTO `resource_file` VALUES (1438771469007552514, '555.jpg', 'example', 'ggg', '2021-09-17 15:47:03', 1);
INSERT INTO `resource_file` VALUES (1438783316012138498, '555.jpg', 'example', 'ggg', '2021-09-17 16:34:08', 1);
INSERT INTO `resource_file` VALUES (1439056843525066753, '555.jpg', 'example', 'ggg', '2021-09-18 10:41:02', 1);
INSERT INTO `resource_file` VALUES (1512263995923988481, '4533.jpg', 'example', 'sadfasf', '2022-04-08 11:00:07', 1);
INSERT INTO `resource_file` VALUES (1512264291882475522, '4533.jpg', 'example', 'sadfasf', '2022-04-08 11:01:18', 1);
INSERT INTO `resource_file` VALUES (1512266182150414337, '4533.jpg', 'example', 'sadfasf', '2022-04-08 11:08:49', 1);
INSERT INTO `resource_file` VALUES (1512273150600634369, '4533.jpg', 'example', 'sadfasf', '2022-04-08 11:36:30', 1);
INSERT INTO `resource_file` VALUES (1512307263462268930, '4533.jpg', 'example', 'sadfasf', '2022-04-08 13:52:03', 1);
INSERT INTO `resource_file` VALUES (1512307296932818946, '4533.jpg', 'example', 'sadfasf', '2022-04-08 13:52:11', 1);
INSERT INTO `resource_file` VALUES (1512632137783255042, '4533.jpg', 'example', 'sadfasf', '2022-04-09 11:22:59', 1);
INSERT INTO `resource_file` VALUES (1513349639777624066, '4533.jpg', 'example', 'sadfasf', '2022-04-11 10:54:05', 1);
INSERT INTO `resource_file` VALUES (1513693360381505538, '4533.jpg', 'example', 'sadfasf', '2022-04-12 09:39:54', 1);
INSERT INTO `resource_file` VALUES (1513700090297577473, '4533.jpg', 'example', 'sadfasf', '2022-04-12 10:06:39', 1);
INSERT INTO `resource_file` VALUES (1513709703218642945, '4533.jpg', 'example', 'sadfasf', '2022-04-12 10:44:51', 1);

SET FOREIGN_KEY_CHECKS = 1;
