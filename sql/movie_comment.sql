/*
Navicat MySQL Data Transfer

Source Server         : fzz
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : movie_all

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-25 22:46:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie_comment
-- ----------------------------
DROP TABLE IF EXISTS `movie_comment`;
CREATE TABLE `movie_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` varchar(255) DEFAULT NULL COMMENT '电影UUID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '电影评价',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
