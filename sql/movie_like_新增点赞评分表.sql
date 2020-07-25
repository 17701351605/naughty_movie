/*
Navicat MySQL Data Transfer

Source Server         : zby
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : movie_all

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-24 10:31:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie_like
-- ----------------------------
DROP TABLE IF EXISTS `movie_like`;
CREATE TABLE `movie_like` (
  `id` int(11) NOT NULL COMMENT '点赞表主键id',
  `movie_id` int(11) DEFAULT NULL COMMENT '电影id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  `is_like` int(11) DEFAULT NULL COMMENT '是否点赞',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
