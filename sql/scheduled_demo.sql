/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.31.100-mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.31.100:3306
 Source Schema         : spring_cloud_alibaba

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 15/06/2023 01:12:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `product_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称',
   `status` int NULL DEFAULT NULL COMMENT '状态',
   `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
   `product_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
   `caption` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
   `inventory` int NULL DEFAULT NULL COMMENT '库存',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES (1, '小米13', 1, 4999.00, 'are you ok', '小米13', 1000);

SET FOREIGN_KEY_CHECKS = 1;
