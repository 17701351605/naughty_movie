/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : movie_all

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-22 22:47:00
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
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` varchar(255) NOT NULL COMMENT '电影UUID',
  `movie_name` varchar(255) DEFAULT NULL COMMENT '电影名称',
  `
actor_name` varchar(255) DEFAULT NULL COMMENT '演员名单',
  `movie_type` int(11) DEFAULT NULL COMMENT '电影类型',
  `long_time` varchar(255) DEFAULT NULL COMMENT '电影片长',
  `top_time` datetime DEFAULT NULL COMMENT '上线时间',
  `status` int(11) DEFAULT NULL COMMENT '0下架，1上架',
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for movie_comment
-- ----------------------------
DROP TABLE IF EXISTS `movie_comment`;
CREATE TABLE `movie_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` varchar(255) DEFAULT NULL COMMENT '电影UUID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `remark` varchar(255) DEFAULT NULL COMMENT '电影评价',
  `is_like` int(255) DEFAULT NULL COMMENT '是否点赞',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  `create_time` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for movie_office
-- ----------------------------
DROP TABLE IF EXISTS `movie_office`;
CREATE TABLE `movie_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` varchar(255) DEFAULT NULL COMMENT '电影UUID',
  `movie_name` varchar(255) DEFAULT NULL COMMENT '电影名称',
  `play_hall` int(11) DEFAULT NULL COMMENT '播放厅',
  `price` decimal(10,2) DEFAULT NULL COMMENT '电影票单价',
  `
seating` int(11) DEFAULT NULL COMMENT '影厅座位数',
  `start_time` datetime DEFAULT NULL COMMENT '电影播放开始时间',
  `is_del` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '邮箱验证码',
  `code_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '0未激活，1激活',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `movie_id` varchar(11) DEFAULT NULL COMMENT '电影UUID',
  `movie_name` varchar(255) DEFAULT NULL COMMENT '电影名称',
  `prices` decimal(10,0) DEFAULT NULL COMMENT '实付总价',
  `buy_number` int(11) DEFAULT NULL COMMENT '购买数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_del` int(255) DEFAULT NULL COMMENT '0退票，1未退票',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
