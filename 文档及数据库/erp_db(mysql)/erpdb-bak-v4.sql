/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.21 : Database - erpdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`erpdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `erpdb`;

/*Table structure for table `dep` */

DROP TABLE IF EXISTS `dep`;

CREATE TABLE `dep` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '部门名称',
  `TELE` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门';

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `USERNAME` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆名',
  `PWD` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登陆密码',
  `NAME` varchar(28) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '真实姓名',
  `GENDER` int DEFAULT NULL COMMENT '性别',
  `EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮件地址',
  `TELE` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `ADDRESS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系地址',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '出生年月日',
  `DEPUUID` bigint DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工';

/*Table structure for table `emp_role` */

DROP TABLE IF EXISTS `emp_role`;

CREATE TABLE `emp_role` (
  `EMPUUID` bigint NOT NULL COMMENT '员工编号',
  `ROLEUUID` bigint NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`EMPUUID`,`ROLEUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工角色';

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `ORIGIN` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产地',
  `PRODUCER` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '厂家',
  `UNIT` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '计量单位',
  `INPRICE` decimal(8,2) DEFAULT NULL COMMENT '进货价格',
  `OUTPRICE` decimal(8,2) DEFAULT NULL COMMENT '销售价格',
  `GOODSTYPEUUID` bigint DEFAULT NULL COMMENT '商品类型',
  `SPEC` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格',
  `MODEL` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '型号',
  `COLOUR` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '颜色',
  `SHELFLIFE` int DEFAULT NULL COMMENT '保质期(天)',
  `BEGINSTORENUM` double DEFAULT NULL COMMENT '期初库存',
  `MINSAFENUM` double DEFAULT NULL COMMENT '最低安全库存',
  `MAXSAFENUM` double DEFAULT NULL COMMENT '最高安全库存',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品';

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '商品类型编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品类型名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类';

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `GOODSUUID` bigint DEFAULT NULL COMMENT '商品',
  `STOREUUID` bigint DEFAULT NULL COMMENT '仓库',
  `NUM` decimal(10,0) DEFAULT NULL COMMENT '数量',
  `TYPE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  `CREATETIME` datetime DEFAULT NULL COMMENT '登记日期',
  `CHECKTIME` datetime DEFAULT NULL COMMENT '审核日期',
  `CREATER` bigint DEFAULT NULL COMMENT '登记人',
  `CHECKER` bigint DEFAULT NULL COMMENT '审核人',
  `STATE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `REMARK` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='盘盈盘亏';

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `MENUID` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单ID',
  `MENUNAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名称',
  `ICON` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `URL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'URL',
  `PID` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上级菜单ID',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单';

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `GOODSUUID` bigint DEFAULT NULL COMMENT '商品编号',
  `GOODSNAME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `SPEC` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '规格',
  `MODEL` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '型号',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `NUM` double DEFAULT NULL COMMENT '订单数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `ENDER` bigint DEFAULT NULL COMMENT '库管员',
  `STOREUUID` bigint DEFAULT NULL COMMENT '仓库编号',
  `STORENUM` decimal(10,2) DEFAULT NULL COMMENT '入库/出库数量',
  `STATE` bigint DEFAULT NULL COMMENT '采购：0=未入库，1=已入库  销售：0=未出库，1=部分出库',
  `ORDERSUUID` bigint DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单明细';

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NOTEDATE` date NOT NULL COMMENT '单据日期',
  `CREATETIME` datetime DEFAULT NULL COMMENT '生成日期',
  `CHECKTIME` datetime DEFAULT NULL COMMENT '审核日期',
  `STARTTIME` datetime DEFAULT NULL COMMENT '确认日期',
  `ENDTIME` datetime DEFAULT NULL COMMENT '入库或出库日期',
  `TYPE` bigint DEFAULT NULL COMMENT '1:采购 2:销售',
  `CREATER` bigint DEFAULT NULL COMMENT '下单员',
  `CHECKER` bigint DEFAULT NULL COMMENT '审核员',
  `STARTER` bigint DEFAULT NULL COMMENT '采购员',
  `ENDER` bigint DEFAULT NULL COMMENT '库管员',
  `SUPPLIERUUID` bigint DEFAULT NULL COMMENT '供应商或客户',
  `TOTALMONEY` decimal(10,2) DEFAULT NULL COMMENT '合计金额',
  `STATE` bigint DEFAULT NULL COMMENT '采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',
  `WAYBILLSN` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运单号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';

/*Table structure for table `returnorderdetail` */

DROP TABLE IF EXISTS `returnorderdetail`;

CREATE TABLE `returnorderdetail` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `GOODSUUID` bigint DEFAULT NULL COMMENT '商品编号',
  `GOODSNAME` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `PRICE` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `NUM` decimal(10,0) DEFAULT NULL COMMENT '数量',
  `MONEY` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `ENDER` bigint DEFAULT NULL COMMENT '库管员',
  `STOREUUID` bigint DEFAULT NULL COMMENT '仓库编号',
  `STATE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `ORDERSUUID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退货订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退货订单明细';

/*Table structure for table `returnorders` */

DROP TABLE IF EXISTS `returnorders`;

CREATE TABLE `returnorders` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NOTEDATE` date DEFAULT NULL COMMENT '单据日期',
  `CREATETIME` datetime DEFAULT NULL COMMENT '生成日期',
  `CHECKTIME` datetime DEFAULT NULL COMMENT '检查日期',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束日期',
  `TYPE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单类型',
  `CREATER` bigint DEFAULT NULL COMMENT '下单员',
  `CHECKER` bigint DEFAULT NULL COMMENT '审核员工编号',
  `ENDER` bigint DEFAULT NULL COMMENT '库管员',
  `SUPPLIERUUID` bigint DEFAULT NULL COMMENT '供应商及客户编号',
  `TOTALMONEY` decimal(10,2) DEFAULT NULL COMMENT '合计金额',
  `STATE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `WAYBILLSN` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '运单号',
  `ORDERSUUID` bigint DEFAULT NULL COMMENT '原订单编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退货订单';

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色';

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `ROLEUUID` bigint NOT NULL,
  `MENUUUID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`ROLEUUID`,`MENUUUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单';

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `EMPUUID` bigint DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='仓库';

/*Table structure for table `storedetail` */

DROP TABLE IF EXISTS `storedetail`;

CREATE TABLE `storedetail` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `STOREUUID` bigint DEFAULT NULL COMMENT '仓库编号',
  `GOODSUUID` bigint DEFAULT NULL COMMENT '商品编号',
  `NUM` double DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='仓库库存';

/*Table structure for table `storeoper` */

DROP TABLE IF EXISTS `storeoper`;

CREATE TABLE `storeoper` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `EMPUUID` bigint DEFAULT NULL COMMENT '操作员工编号',
  `OPERTIME` datetime DEFAULT NULL COMMENT '操作日期',
  `STOREUUID` bigint DEFAULT NULL COMMENT '仓库编号',
  `GOODSUUID` bigint DEFAULT NULL COMMENT '商品编号',
  `NUM` double DEFAULT NULL COMMENT '数量',
  `TYPE` int DEFAULT NULL COMMENT '1：入库 2：出库',
  `OPERDESC` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '操作描述',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='仓库操作记录';

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `UUID` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `NAME` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `ADDRESS` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系地址',
  `CONTACT` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系人',
  `TELE` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮件地址',
  `TYPE` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '1：供应商 2：客户',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='供应商';

/*Table structure for table `waybill` */

DROP TABLE IF EXISTS `waybill`;

CREATE TABLE `waybill` (
  `sn` bigint NOT NULL AUTO_INCREMENT COMMENT '运单号',
  `userid` bigint DEFAULT NULL COMMENT '用户ID',
  `toaddress` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `addressee` varchar(100) DEFAULT NULL COMMENT '收货人',
  `tele` varchar(100) DEFAULT NULL COMMENT '收件人电话',
  `info` varchar(2000) DEFAULT NULL COMMENT '运单详情',
  `state` varchar(1) DEFAULT NULL COMMENT '运单状态',
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='运单';

/*Table structure for table `waybilldetail` */

DROP TABLE IF EXISTS `waybilldetail`;

CREATE TABLE `waybilldetail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sn` bigint DEFAULT NULL COMMENT '运单号',
  `exedate` varchar(10) DEFAULT NULL COMMENT '执行日期',
  `exetime` varchar(10) DEFAULT NULL COMMENT '执行时间',
  `info` varchar(100) DEFAULT NULL COMMENT '执行信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `view_storealert` */

DROP TABLE IF EXISTS `view_storealert`;

/*!50001 DROP VIEW IF EXISTS `view_storealert` */;
/*!50001 DROP TABLE IF EXISTS `view_storealert` */;

/*!50001 CREATE TABLE  `view_storealert`(
 `uuid` bigint ,
 `name` varchar(30) ,
 `storenum` decimal(18,2) ,
 `innum` decimal(18,2) ,
 `outnum` decimal(18,2) 
)*/;

/*View structure for view view_storealert */

/*!50001 DROP TABLE IF EXISTS `view_storealert` */;
/*!50001 DROP VIEW IF EXISTS `view_storealert` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `view_storealert` AS select `a`.`uuid` AS `uuid`,`a`.`name` AS `name`,`a`.`storenum` AS `storenum`,`b`.`innum` AS `innum`,`c`.`outnum` AS `outnum` from (((select `g`.`UUID` AS `uuid`,`g`.`NAME` AS `name`,cast((ifnull(sum(`s`.`NUM`),0) + ifnull(`g`.`BEGINSTORENUM`,0)) as decimal(18,2)) AS `storenum` from (`goods` `g` left join `storedetail` `s` on((`g`.`UUID` = `s`.`GOODSUUID`))) group by `g`.`UUID`,`g`.`NAME`) `a` join (select `od`.`GOODSUUID` AS `goodsuuid`,cast(sum((`od`.`NUM` - ifnull(`od`.`STORENUM`,0))) as decimal(18,2)) AS `innum` from (`orderdetail` `od` join `orders` `o`) where ((`od`.`ORDERSUUID` = `o`.`UUID`) and (`o`.`TYPE` = 1) and (`od`.`STATE` < 2)) group by `od`.`GOODSUUID`) `b`) join (select `od`.`GOODSUUID` AS `goodsuuid`,cast(sum((`od`.`NUM` - ifnull(`od`.`STORENUM`,0))) as decimal(18,2)) AS `outnum` from (`orderdetail` `od` join `orders` `o`) where ((`od`.`ORDERSUUID` = `o`.`UUID`) and (`o`.`TYPE` = 2) and (`od`.`STATE` < 2)) group by `od`.`GOODSUUID`) `c`) where ((`a`.`uuid` = `b`.`goodsuuid`) and (`a`.`uuid` = `c`.`goodsuuid`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
