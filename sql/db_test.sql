/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : db_test

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2024-06-27 13:11:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `province_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='地址簿';

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int(11) DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '分类名称',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int(11) DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品及套餐分类';

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论ID，自增，不为空',
  `content` varchar(300) NOT NULL COMMENT '评论内容，不为空',
  `consumer_user_id` bigint(20) NOT NULL COMMENT '用户ID，不为空',
  `dish_id` bigint(20) NOT NULL COMMENT '菜品ID，不为空',
  `comment_like` int(11) NOT NULL DEFAULT '0' COMMENT '评论的点赞数，默认为0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '0表示未删除，1表示已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='评论表';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for comment_rel
-- ----------------------------
DROP TABLE IF EXISTS `comment_rel`;
CREATE TABLE `comment_rel` (
  `id` bigint(20) NOT NULL COMMENT '关系ID，不为空',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级评论ID',
  `child_id` bigint(20) NOT NULL COMMENT '子级评论ID，不为空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='评论关系表';

-- ----------------------------
-- Records of comment_rel
-- ----------------------------

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '回复ID，自增，不为空',
  `content` varchar(300) NOT NULL COMMENT '回复内容，不为空',
  `consumer_user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `comment_id` bigint(20) NOT NULL COMMENT '评论ID，不为空',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(11) NOT NULL DEFAULT '0' COMMENT '0表示未删除，1表示已删除',
  `comment_like` int(11) DEFAULT '0' COMMENT '表示该评论喜欢的人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='回复表';

-- ----------------------------
-- Records of comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for consumer_user
-- ----------------------------
DROP TABLE IF EXISTS `consumer_user`;
CREATE TABLE `consumer_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '微信用户唯一标识',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息';

-- ----------------------------
-- Records of consumer_user
-- ----------------------------

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `status` int(11) DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_user` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品';

-- ----------------------------
-- Records of dish
-- ----------------------------

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint(20) NOT NULL COMMENT '菜品id',
  `name` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '口味名称',
  `value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜品口味关系表';

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
  `user_id` bigint(20) NOT NULL COMMENT '下单用户',
  `table_id` int(11) DEFAULT NULL COMMENT '桌号',
  `address_id` bigint(20) NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime DEFAULT NULL COMMENT '结账时间',
  `pay_method` int(11) NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `pay_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态 0未支付 1已支付 2退款',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  `cancel_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '订单拒绝原因',
  `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间',
  `delivery_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配送状态  1立即送出  0选择具体时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '送达时间',
  `pack_amount` int(11) DEFAULT NULL COMMENT '打包费',
  `tableware_number` int(11) DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片地址',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `dish_id` bigint(20) DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint(20) DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '口味 已经选择的口味',
  `number` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单明细表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '套餐名称',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述信息',
  `image_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片url',
  `business_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `category_id` bigint(20) NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int(11) DEFAULT '0' COMMENT '售卖状态 0:停售 1:起售',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐';

-- ----------------------------
-- Records of setmeal
-- ----------------------------

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `setmeal_id` bigint(20) DEFAULT NULL COMMENT '套餐id',
  `dish_id` bigint(20) DEFAULT NULL COMMENT '菜品id',
  `copies` int(11) DEFAULT NULL COMMENT '菜品份数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='套餐菜品关系表';

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------

-- ----------------------------
-- Table structure for sys_business
-- ----------------------------
DROP TABLE IF EXISTS `sys_business`;
CREATE TABLE `sys_business` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `account` varchar(20) DEFAULT NULL COMMENT '商家账户',
  `password` varchar(20) DEFAULT NULL COMMENT '商家密码',
  `address_id` int(11) DEFAULT NULL COMMENT '地址簿Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父商家',
  `phone` int(11) DEFAULT NULL COMMENT '店铺负责人手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '商家邮箱',
  `logo_url` int(11) DEFAULT NULL COMMENT '商家头像url',
  `status` int(11) DEFAULT '0' COMMENT '商家店铺启用状态 0未启用 1已经启用',
  `create_time` datetime DEFAULT NULL COMMENT '商家创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '商家更新时间',
  `is_delete` int(11) DEFAULT NULL COMMENT '逻辑删除 0未删除 已删除',
  `role_id` int(11) DEFAULT '1' COMMENT '权限id 1普通商家',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商家表';

-- ----------------------------
-- Records of sys_business
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `business_id` bigint(20) DEFAULT NULL COMMENT '商铺id',
  `log_content` varchar(255) NOT NULL COMMENT '操作内容',
  `log_result` varchar(255) DEFAULT NULL COMMENT '操作结果',
  `input_params` text COMMENT '入参字符串',
  `output_params` varchar(1024) DEFAULT NULL COMMENT '出参 json字符串',
  `create_time` datetime DEFAULT NULL COMMENT '日志发生事件，用时间戳便于排序',
  `is_delete` int(11) DEFAULT '0' COMMENT '0未删除 1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_log_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统菜单Id',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '系统菜单名',
  `menu_component` varchar(255) DEFAULT NULL COMMENT '系统菜单组件',
  `menu_path` varchar(255) DEFAULT NULL COMMENT '系统菜单路径',
  `menu_icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `menu_status` int(11) DEFAULT '1' COMMENT '菜单是否启用1启用0禁用',
  `menu_parent_id` int(11) DEFAULT '0' COMMENT '所属父级菜单id',
  `menu_is_hidden` int(11) DEFAULT '0' COMMENT '菜单是否隐藏1隐藏0未隐藏',
  `menu_sort` int(11) DEFAULT '0' COMMENT '菜单排序-数字越大排在越上面',
  `create_time` datetime COMMENT '菜单创建时间',
  `update_time` datetime ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否逻辑删除 0未删除 1删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_menu_sys_menu_id_uindex` (`id`),
  UNIQUE KEY `sys_menu_menu_component_uindex` (`menu_component`),
  UNIQUE KEY `sys_menu_menu_name_uindex` (`menu_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统角色表id',
  `sys_role_id` bigint(20) NOT NULL COMMENT '系统角色id',
  `sys_role_name` varchar(255) NOT NULL COMMENT '系统角色名',
  UNIQUE KEY `sys_role_sys_role_id_uindex` (`sys_role_id`),
  UNIQUE KEY `sys_role_sys_role_name_uindex` (`sys_role_name`),
  UNIQUE KEY `sys_role_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统角色菜单关联表id',
  `role_id` bigint(20) NOT NULL COMMENT '角色Id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单Id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_role_menu_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint(20) unsigned DEFAULT NULL COMMENT '商铺id',
  `title` varchar(255) NOT NULL COMMENT '代金券标题',
  `sub_title` varchar(255) DEFAULT NULL COMMENT '副标题',
  `rules` varchar(1024) DEFAULT NULL COMMENT '使用规则',
  `pay_value` bigint(20) unsigned NOT NULL COMMENT '支付金额，单位是分。例如200代表2元',
  `actual_value` bigint(20) NOT NULL COMMENT '抵扣金额，单位是分。例如200代表2元',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0,普通券；1,秒杀券',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '1,上架; 2,下架; 3,过期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠卷表';

-- ----------------------------
-- Records of voucher
-- ----------------------------

-- ----------------------------
-- Table structure for voucher_order
-- ----------------------------
DROP TABLE IF EXISTS `voucher_order`;
CREATE TABLE `voucher_order` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '下单的用户id',
  `voucher_id` bigint(20) unsigned NOT NULL COMMENT '购买的代金券id',
  `pay_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '支付方式 1：余额支付；2：支付宝；3：微信',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '订单状态，1：未支付；2：已支付；3：已核销；4：已取消；5：退款中；6：已退款',
  `create_time` datetime NOT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `use_time` datetime DEFAULT NULL COMMENT '核销时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠卷订单表';

-- ----------------------------
-- Records of voucher_order
-- ----------------------------

-- ----------------------------
-- Table structure for voucher_seckill
-- ----------------------------
DROP TABLE IF EXISTS `voucher_seckill`;
CREATE TABLE `voucher_seckill` (
  `voucher_id` bigint(20) unsigned NOT NULL COMMENT '关联的优惠券的id',
  `stock` int(11) NOT NULL COMMENT '库存',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `begin_time` datetime NOT NULL COMMENT '生效时间',
  `end_time` datetime NOT NULL COMMENT '失效时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`voucher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀优惠券表，与优惠券是一对一关系';

-- ----------------------------
-- Records of voucher_seckill
-- ----------------------------
