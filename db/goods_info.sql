/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2018-04-24 17:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_info
-- ----------------------------
DROP TABLE IF EXISTS `goods_info`;
CREATE TABLE `goods_info` (
  `goods_info_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `stock` bigint(10) DEFAULT NULL COMMENT '库存',
  PRIMARY KEY (`goods_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_info
-- ----------------------------
INSERT INTO `goods_info` VALUES ('1', '90');
INSERT INTO `goods_info` VALUES ('2', '10');
INSERT INTO `goods_info` VALUES ('3', '7');
INSERT INTO `goods_info` VALUES ('4', '14');
INSERT INTO `goods_info` VALUES ('5', '67');
INSERT INTO `goods_info` VALUES ('6', '124');
INSERT INTO `goods_info` VALUES ('7', '52');
INSERT INTO `goods_info` VALUES ('8', '92');
INSERT INTO `goods_info` VALUES ('9', '123');
INSERT INTO `goods_info` VALUES ('10', '51');
INSERT INTO `goods_info` VALUES ('11', '5634');
INSERT INTO `goods_info` VALUES ('12', '31');
INSERT INTO `goods_info` VALUES ('13', '1');
INSERT INTO `goods_info` VALUES ('14', '2');
INSERT INTO `goods_info` VALUES ('27', '1');
INSERT INTO `goods_info` VALUES ('28', '2');
INSERT INTO `goods_info` VALUES ('29', '1');
INSERT INTO `goods_info` VALUES ('30', '2');
