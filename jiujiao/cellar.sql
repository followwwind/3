/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : winedb

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 09/03/2021 07:29:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tea
-- ----------------------------
DROP TABLE IF EXISTS `tea`;
CREATE TABLE `tea`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `year` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `grapes` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `country` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `region` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tea
-- ----------------------------
INSERT INTO `tea` VALUES (1, 'CHATEAU DE SAINT COSME', '2009', 'Grenache / Syrah', 'France', 'Southern Rhone / Gigondas', '2021-03-09 07:08:54');
INSERT INTO `tea` VALUES (2, 'LAN RIOJA CRIANZA', '2006', 'Tempranillo', 'Spain', 'Rioja', '2021-03-09 07:08:57');
INSERT INTO `tea` VALUES (3, 'MARGERUM SYBARITE', '2010', 'Sauvignon Blanc', 'USA', 'California Central Cosat', '2021-03-09 07:08:59');
INSERT INTO `tea` VALUES (4, 'OWEN ROE \"EX UMBRIS\"', '2009', 'Syrah', 'USA', 'Washington', '2021-03-09 07:09:02');
INSERT INTO `tea` VALUES (5, 'REX HILL', '2009', 'Pinot Noir', 'USA', 'Oregon', '2021-03-09 07:09:04');
INSERT INTO `tea` VALUES (6, 'VITICCIO CLASSICO RISERVA', '2007', 'Sangiovese Merlot', 'Italy', 'Tuscany', '2021-03-09 07:09:08');
INSERT INTO `tea` VALUES (7, 'CHATEAU LE DOYENNE', '2005', 'Merlot', 'France', 'Bordeaux', '2021-03-09 07:09:11');
INSERT INTO `tea` VALUES (8, 'DOMAINE DU BOUSCAT', '2009', 'Merlot', 'France', 'Bordeaux', '2021-03-09 07:09:15');
INSERT INTO `tea` VALUES (9, 'BLOCK NINE', '2009', 'Pinot Noir', 'USA', 'California', '2021-03-09 07:09:17');
INSERT INTO `tea` VALUES (10, 'DOMAINE SERENE', '2007', 'Pinot Noir', 'USA', 'Oregon', '2021-03-09 07:09:19');
INSERT INTO `tea` VALUES (11, 'BODEGA LURTON', '2011', 'Pinot Gris', 'Argentina', 'Mendoza', '2021-03-09 07:09:21');
INSERT INTO `tea` VALUES (12, 'LES MORIZOTTES', '2009', 'Chardonnay', 'France', 'Burgundy', '2021-03-09 07:09:23');

SET FOREIGN_KEY_CHECKS = 1;
