/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : movie_all

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 23:19:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for base_data
-- ----------------------------
DROP TABLE IF EXISTS `base_data`;
CREATE TABLE `base_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_data
-- ----------------------------
INSERT INTO `base_data` VALUES ('1', '电影类型', '0', '1');
INSERT INTO `base_data` VALUES ('2', '战争', '1', '1');
INSERT INTO `base_data` VALUES ('3', '武侠', '1', '1');
INSERT INTO `base_data` VALUES ('4', '动漫', '1', '1');
INSERT INTO `base_data` VALUES ('5', '科幻', '1', '1');
INSERT INTO `base_data` VALUES ('6', '悬疑', '1', '1');
INSERT INTO `base_data` VALUES ('7', '恐怖', '1', '1');
INSERT INTO `base_data` VALUES ('8', '爱情', '1', '1');
INSERT INTO `base_data` VALUES ('9', '播放厅', '0', '1');
INSERT INTO `base_data` VALUES ('10', '一号播放厅', '9', '1');
INSERT INTO `base_data` VALUES ('11', '二号播放厅', '9', '1');
INSERT INTO `base_data` VALUES ('12', '三号播放厅', '9', '1');
