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

/*Data for the table `dep` */

insert  into `dep`(`UUID`,`NAME`,`TELE`) values 
(1,'管理员组','000000'),
(2,'总裁办','111111'),
(3,'采购部','222222'),
(4,'销售部','333333'),
(5,'公关部','444444'),
(6,'行政部','555555'),
(7,'人事部','555555'),
(8,'运输部','444444'),
(9,'党办','555555'),
(10,'工会','555555'),
(11,'仓储部','555555'),
(12,'客服部','555555'),
(13,'财务部','555555'),
(14,'test','12345');

/*Data for the table `emp` */

insert  into `emp`(`UUID`,`USERNAME`,`PWD`,`NAME`,`GENDER`,`EMAIL`,`TELE`,`ADDRESS`,`BIRTHDAY`,`DEPUUID`) values 
(1,'admin','3ef7164d1f6167cb9f2658c07d3c2f0a','超级管理员',0,'admin@itcast.cn','12345678','建材城西路中腾商务大厦','1959-10-01 00:00:00',1),
(2,'sunwukong','8e2f9f83576dfd59d9e6d23ba7c61ef6','孙悟空',1,'swk@itcast.cn','12345678','花果山水帘洞11-2','1969-10-01 00:00:00',2),
(3,'tangseng','tangseng','唐僧',1,'ts@itcast.cn','12345678','东?链筇菩∏?0-14','1949-10-01 00:00:00',2),
(4,'zhubajie','zhubajie','猪八戒',1,'zbj@itcast.cn','12345678','高老庄生态园2-3','1949-10-01 00:00:00',3),
(5,'shaheshang','shaheshang','沙和尚',1,'shs@itcast.cn','12345678','流沙河风景区4-4','1949-10-01 00:00:00',3),
(6,'bailongma','b815d6dc9a57bae4acd55c456fef6bb7','白龙马',1,'blm@itcast.cn','12345678','西海家园4-9-1','1949-10-01 00:00:00',3),
(7,'test','5cbaca32e76bb49ca69657a9145d77ee','test',0,'test@qq.com','12345','','2021-12-17 00:00:00',1);

/*Data for the table `emp_role` */

insert  into `emp_role`(`EMPUUID`,`ROLEUUID`) values 
(1,1),
(2,2),
(6,4);

/*Data for the table `goods` */

insert  into `goods`(`UUID`,`NAME`,`ORIGIN`,`PRODUCER`,`UNIT`,`INPRICE`,`OUTPRICE`,`GOODSTYPEUUID`,`SPEC`,`MODEL`,`COLOUR`,`SHELFLIFE`,`BEGINSTORENUM`,`MINSAFENUM`,`MAXSAFENUM`) values 
(1,'水蜜桃','北京','北京水果之乡','斤',2.34,4.23,1,'GH','123','红色',NULL,NULL,NULL,NULL),
(2,'大鸭梨','北京','北京水果之乡','斤',1.11,3.55,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'猕猴桃','北京','北京水果之乡','斤',6.33,9.02,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(4,'甜面酱','北京','七必居','袋',4.11,6.33,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(5,'可比克','北京','北京山寨食品有限公司','袋',3.88,6.33,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(6,'好吃点','河北','河北好吃点食品','袋',4.22,5.21,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(8,'耗油','深圳','海天一号','瓶',8.00,10.00,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(9,'盐','','','袋',7.00,9.00,2,'500g/袋','XL2','',NULL,100.1,NULL,NULL);

/*Data for the table `goodstype` */

insert  into `goodstype`(`UUID`,`NAME`) values 
(1,'水果'),
(2,'调味品'),
(3,'儿童食品');

/*Data for the table `inventory` */

/*Data for the table `menu` */

insert  into `menu`(`MENUID`,`MENUNAME`,`ICON`,`URL`,`PID`) values 
('0','系统菜单','icon-sys',NULL,NULL),
('100','基础数据','icon-sys',NULL,'0'),
('101','商品类型','icon-sys','goodstype.html','100'),
('102','商品','icon-sys','goods.html','100'),
('111','供应商','icon-sys','supplier.html?type=1','100'),
('112','客户','icon-sys','supplier.html?type=2','100'),
('133','仓库','icon-sys','store.html','100'),
('200','人事管理','icon-sys',NULL,'0'),
('201','部门','icon-sys','dep.html','200'),
('202','员工','icon-sys','emp.html','200'),
('300','采购管理','icon-sys','','0'),
('301','采购订单查询','icon-sys','orders.html?type=1&oper=orders','300'),
('302','采购申请','icon-sys','orders.html?type=1&oper=myorders','300'),
('303','采购审核','icon-sys','orders.html?type=1&oper=doCheck','300'),
('304','采购确认','icon-sys','orders.html?type=1&oper=doStart','300'),
('305','采购入库','icon-sys','orders.html?type=1&oper=doInStore','300'),
('400','销售管理','icon-sys',NULL,'0'),
('401','销售订单查询','icon-sys','orders.html?type=2&oper=orders','400'),
('402','销售订单录入','icon-sys','orders.html?type=2&oper=myorders','400'),
('403','销售出库','icon-sys','orders.html?type=2&oper=doOutStore','400'),
('500','库存管理','icon-sys',NULL,'0'),
('501','库存查询','icon-sys','storedetail.html','500'),
('502','库存变动记录查询','icon-sys','storeoper.html','500'),
('503','库存预警','icon-sys','storealert.html','500'),
('600','统计分析','icon-sys',NULL,'0'),
('601','销售统计','icon-sys','report_order.html','600'),
('602','销售趋势分析','icon-sys','report_trend.html','600'),
('700','权限管理','icon-sys',NULL,'0'),
('701','重置密码','icon-sys','pwd.html','700'),
('702','角色管理','icon-sys','role.html','700'),
('703','角色权限设置','icon-sys','roleMenuSet.html','700'),
('704','用户角色设置','icon-sys','empRoleSet.html','700'),
('710','菜单','icon-sys','menu.html','700');

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`UUID`,`GOODSUUID`,`GOODSNAME`,`SPEC`,`MODEL`,`PRICE`,`NUM`,`MONEY`,`ENDTIME`,`ENDER`,`STOREUUID`,`STORENUM`,`STATE`,`ORDERSUUID`) values 
(1,1,'水蜜桃',NULL,NULL,2.34,1,2.34,'2021-12-24 12:01:19',1,2,1.00,2,1),
(2,2,'大鸭梨',NULL,NULL,1.11,3,3.33,'2021-12-24 12:01:54',1,2,3.00,2,1),
(3,5,'可比克',NULL,NULL,3.88,3,11.64,NULL,NULL,NULL,NULL,0,2),
(4,6,'好吃点',NULL,NULL,4.22,4,16.88,NULL,NULL,NULL,NULL,0,2),
(9,5,'可比克',NULL,NULL,3.88,3,11.64,'2021-12-24 12:06:32',1,1,1.00,1,5),
(10,6,'好吃点',NULL,NULL,4.22,4,16.88,NULL,NULL,NULL,NULL,0,5),
(13,3,'猕猴桃',NULL,NULL,6.33,3,18.99,'2021-12-24 11:59:17',1,2,3.00,2,8),
(14,5,'可比克',NULL,NULL,3.88,3,11.64,NULL,NULL,NULL,NULL,0,9),
(15,1,'水蜜桃',NULL,NULL,2.34,1,2.57,NULL,NULL,NULL,NULL,0,10),
(16,2,'大鸭梨',NULL,NULL,1.11,1,1.33,NULL,NULL,NULL,NULL,0,11),
(17,3,'猕猴桃',NULL,NULL,6.33,1,6.96,NULL,NULL,NULL,NULL,0,11),
(18,4,'甜面酱',NULL,NULL,4.11,2,8.22,NULL,NULL,NULL,NULL,0,11),
(19,1,'水蜜桃',NULL,NULL,2.34,1.11,2.60,NULL,NULL,NULL,NULL,0,12),
(20,1,'水蜜桃',NULL,NULL,2.34,1.11,2.60,NULL,NULL,NULL,NULL,0,13),
(21,1,'水蜜桃',NULL,NULL,2.34,1.11,2.60,NULL,NULL,NULL,0.00,0,14),
(22,1,'水蜜桃',NULL,NULL,2.34,100,234.00,NULL,NULL,NULL,NULL,0,15),
(23,1,'水蜜桃',NULL,NULL,2.34,200,468.00,NULL,NULL,NULL,NULL,0,16),
(24,2,'大鸭梨',NULL,NULL,1.11,1000,1110.00,NULL,NULL,NULL,NULL,0,17),
(25,3,'猕猴桃',NULL,NULL,6.33,50,316.50,NULL,NULL,NULL,NULL,0,18),
(26,3,'猕猴桃',NULL,NULL,6.33,1,6.33,NULL,NULL,NULL,NULL,0,19),
(27,3,'猕猴桃',NULL,NULL,6.33,1,6.33,NULL,NULL,NULL,NULL,0,20),
(28,3,'猕猴桃',NULL,NULL,6.33,20,126.60,NULL,NULL,NULL,NULL,0,21),
(29,4,'甜面酱',NULL,NULL,4.11,30,123.30,NULL,NULL,NULL,NULL,0,21),
(30,2,'大鸭梨',NULL,NULL,1.11,1,1.11,'2022-01-26 17:53:44',1,2,1.00,2,22),
(31,2,'大鸭梨',NULL,NULL,1.11,200,222.00,NULL,NULL,NULL,NULL,0,23),
(32,3,'猕猴桃',NULL,NULL,6.33,100,633.00,NULL,NULL,NULL,NULL,0,23),
(33,1,'水蜜桃',NULL,NULL,2.34,20,46.80,NULL,NULL,NULL,NULL,0,24),
(34,1,'水蜜桃',NULL,NULL,2.34,100,234.00,'2022-01-11 10:45:29',1,2,1.00,1,25),
(35,2,'大鸭梨',NULL,NULL,1.11,200,222.00,NULL,NULL,NULL,NULL,0,25),
(36,5,'可比克',NULL,NULL,3.88,300,1164.00,'2022-01-13 10:27:54',1,2,300.00,2,26),
(37,6,'好吃点',NULL,NULL,4.22,200,844.00,'2022-01-13 10:28:03',1,1,200.00,2,26),
(38,5,'可比克',NULL,NULL,3.88,20,77.60,'2022-01-13 10:29:04',1,2,20.00,2,27),
(39,6,'好吃点',NULL,NULL,4.22,10,42.20,'2022-01-13 10:29:17',1,1,10.00,2,28),
(40,4,'甜面酱',NULL,NULL,4.11,200,822.00,NULL,NULL,NULL,NULL,0,29),
(41,4,'甜面酱',NULL,NULL,4.11,30,123.30,NULL,NULL,NULL,NULL,0,30),
(42,3,'猕猴桃',NULL,NULL,6.33,500,3165.00,'2022-01-14 10:42:35',1,2,500.00,2,31),
(43,1,'水蜜桃',NULL,NULL,2.34,200,468.00,'2022-01-14 10:42:26',1,1,200.00,2,32),
(44,1,'水蜜桃',NULL,NULL,2.34,20,46.80,NULL,NULL,NULL,NULL,0,33),
(45,1,'水蜜桃',NULL,NULL,2.34,2,4.68,NULL,NULL,NULL,NULL,0,34),
(46,3,'猕猴桃',NULL,NULL,6.33,10,63.30,NULL,NULL,NULL,NULL,0,35),
(47,3,'猕猴桃',NULL,NULL,6.33,11,69.63,NULL,NULL,NULL,NULL,0,36),
(48,3,'猕猴桃',NULL,NULL,6.33,21,132.93,NULL,NULL,NULL,NULL,0,37),
(49,3,'猕猴桃',NULL,NULL,6.33,12,75.96,NULL,NULL,NULL,NULL,0,38),
(50,3,'猕猴桃',NULL,NULL,6.33,23,145.59,NULL,NULL,NULL,NULL,0,39),
(51,3,'猕猴桃',NULL,NULL,6.33,13,82.29,NULL,NULL,NULL,NULL,0,40),
(52,1,'水蜜桃',NULL,NULL,2.34,12,28.08,NULL,NULL,NULL,NULL,0,41),
(53,1,'水蜜桃','GH','123',2.34,100,234.00,NULL,NULL,NULL,NULL,0,42),
(54,1,'水蜜桃','GH','123',2.34,200,468.00,NULL,NULL,NULL,NULL,0,43);

/*Data for the table `orders` */

insert  into `orders`(`UUID`,`NOTEDATE`,`CREATETIME`,`CHECKTIME`,`STARTTIME`,`ENDTIME`,`TYPE`,`CREATER`,`CHECKER`,`STARTER`,`ENDER`,`SUPPLIERUUID`,`TOTALMONEY`,`STATE`,`WAYBILLSN`) values 
(1,'2021-12-21','2021-12-21 15:39:45','2021-12-23 10:06:07','2021-12-24 11:47:50','2021-12-24 12:01:54',1,1,1,1,1,1,5.67,3,NULL),
(2,'2021-12-10','2021-12-21 15:55:18','2021-12-24 12:02:41',NULL,NULL,1,7,1,NULL,NULL,3,28.52,1,NULL),
(3,'2021-12-10','2021-12-21 16:18:04',NULL,NULL,NULL,1,7,3,NULL,NULL,3,15.10,0,NULL),
(4,'2021-12-10','2021-12-21 16:23:28',NULL,NULL,NULL,1,7,4,NULL,NULL,3,15.10,0,NULL),
(5,'2021-11-10','2021-12-21 16:33:27','2021-12-24 12:06:11','2021-12-24 12:06:17',NULL,1,1,1,1,NULL,3,28.52,2,NULL),
(8,'2021-10-10','2021-12-21 17:16:11','2021-12-22 20:27:31','2021-12-22 20:43:47','2021-12-24 11:59:17',1,1,1,1,1,1,18.99,3,NULL),
(9,'2021-10-10','2021-12-21 17:40:05',NULL,NULL,NULL,1,1,2,NULL,NULL,1,11.64,0,NULL),
(10,'2021-09-22','2021-12-23 15:10:59',NULL,NULL,NULL,1,NULL,1,NULL,NULL,1,2.57,0,NULL),
(11,'2021-09-22','2021-12-23 15:20:52',NULL,NULL,NULL,1,NULL,1,NULL,NULL,3,16.51,0,NULL),
(12,'2021-09-22','2021-12-23 15:28:32',NULL,NULL,NULL,1,NULL,1,NULL,NULL,1,2.60,0,NULL),
(13,'2021-08-20','2021-12-23 15:30:55','2021-12-24 11:47:24','2021-12-24 11:48:02',NULL,1,NULL,1,1,NULL,1,2.60,2,NULL),
(14,'2021-08-20','2021-12-23 15:32:56','2021-12-24 11:47:20',NULL,NULL,1,NULL,1,NULL,NULL,1,2.60,1,NULL),
(15,'2021-08-20','2021-12-24 17:32:30',NULL,NULL,NULL,1,NULL,1,NULL,NULL,2,234.00,0,NULL),
(16,'2021-07-20','2021-12-24 17:39:13',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,468.00,0,NULL),
(17,'2021-07-20','2021-12-24 17:43:24',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,1110.00,0,NULL),
(18,'2021-07-20','2021-12-24 17:46:22',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,316.50,0,NULL),
(19,'2021-06-16','2021-12-24 17:58:38',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,6.33,0,NULL),
(20,'2021-06-16','2021-12-24 18:00:13',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,6.33,0,NULL),
(21,'2021-06-16','2021-12-24 18:23:26',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,249.90,0,NULL),
(22,'2021-05-16','2021-12-31 16:58:25',NULL,NULL,'2022-01-26 17:53:44',2,NULL,1,NULL,1,2,1.11,1,NULL),
(23,'2021-05-16','2021-12-31 17:09:01',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,855.00,0,NULL),
(24,'2021-05-16','2021-12-31 17:31:22',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,46.80,0,NULL),
(25,'2021-05-15','2022-01-11 10:42:10',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,456.00,0,NULL),
(26,'2021-04-15','2022-01-13 10:27:28','2022-01-13 10:27:37','2022-01-13 10:27:42','2022-01-13 10:28:03',1,1,1,1,1,3,2008.00,3,NULL),
(27,'2021-04-15','2022-01-13 10:28:34',NULL,NULL,'2022-01-13 10:29:04',2,1,NULL,NULL,1,2,77.60,1,NULL),
(28,'2021-03-11','2022-01-13 10:28:45',NULL,NULL,'2022-01-13 10:29:17',2,1,NULL,NULL,1,2,42.20,1,'6'),
(29,'2021-03-11','2022-01-13 19:46:44','2022-01-14 10:40:08','2022-01-14 10:42:11',NULL,1,1,1,1,NULL,1,822.00,2,NULL),
(30,'2021-02-26','2022-01-13 20:01:59','2022-01-14 10:40:12','2022-01-14 10:42:08',NULL,1,1,1,1,NULL,1,123.30,2,NULL),
(31,'2021-01-26','2022-01-13 20:10:02','2022-01-14 10:40:15','2022-01-14 10:42:01','2022-01-14 10:42:35',1,1,1,1,1,3,3165.00,3,NULL),
(32,'2022-01-26','2022-01-13 20:27:49','2022-01-14 10:41:45','2022-01-14 10:42:05','2022-01-14 10:42:26',1,1,1,1,1,1,468.00,3,NULL),
(33,'2022-01-14','2022-01-14 10:42:58',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,46.80,0,NULL),
(34,'2021-11-10','2022-01-14 10:50:57',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,4.68,0,NULL),
(35,'2021-10-15','2022-01-14 10:51:33',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,63.30,0,NULL),
(36,'2021-09-14','2022-01-14 10:51:49',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,69.63,0,NULL),
(37,'2021-08-18','2022-01-14 10:52:01',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,132.93,0,NULL),
(38,'2021-07-13','2022-01-14 10:52:36',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,75.96,0,NULL),
(39,'2021-06-09','2022-01-14 10:52:48',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,145.59,0,NULL),
(40,'2021-05-26','2022-01-14 10:53:00',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,82.29,0,NULL),
(41,'2021-04-21','2022-01-14 10:53:15',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,28.08,0,NULL),
(42,'2022-01-18','2022-01-18 16:30:44',NULL,NULL,NULL,1,1,NULL,NULL,NULL,1,234.00,0,NULL),
(43,'2022-01-18','2022-01-18 16:34:34',NULL,NULL,NULL,2,1,NULL,NULL,NULL,2,468.00,0,'7');

/*Data for the table `returnorderdetail` */

/*Data for the table `returnorders` */

/*Data for the table `role` */

insert  into `role`(`UUID`,`NAME`) values 
(1,'超级管理员'),
(2,'采购员'),
(3,'采购经理'),
(4,'库管员');

/*Data for the table `role_menu` */

insert  into `role_menu`(`ROLEUUID`,`MENUUUID`) values 
(1,'100'),
(1,'101'),
(1,'102'),
(1,'111'),
(1,'112'),
(1,'133'),
(1,'200'),
(1,'201'),
(1,'202'),
(1,'300'),
(1,'301'),
(1,'302'),
(1,'303'),
(1,'304'),
(1,'305'),
(1,'400'),
(1,'401'),
(1,'402'),
(1,'403'),
(1,'500'),
(1,'501'),
(1,'502'),
(1,'503'),
(1,'600'),
(1,'601'),
(1,'602'),
(1,'700'),
(1,'701'),
(1,'702'),
(1,'703'),
(1,'704'),
(1,'710'),
(2,'101'),
(2,'102'),
(2,'111'),
(2,'301'),
(2,'302'),
(2,'304'),
(4,'133'),
(4,'305'),
(4,'403'),
(4,'500'),
(4,'501'),
(4,'502'),
(4,'503');

/*Data for the table `store` */

insert  into `store`(`UUID`,`NAME`,`EMPUUID`) values 
(1,'冷藏库',NULL),
(2,'常温室',NULL);

/*Data for the table `storedetail` */

insert  into `storedetail`(`UUID`,`STOREUUID`,`GOODSUUID`,`NUM`) values 
(5,2,3,503),
(6,2,1,0),
(7,2,2,2),
(8,1,5,1),
(9,2,5,280),
(10,1,6,190),
(11,1,1,200);

/*Data for the table `storeoper` */

insert  into `storeoper`(`UUID`,`EMPUUID`,`OPERTIME`,`STOREUUID`,`GOODSUUID`,`NUM`,`TYPE`,`OPERDESC`) values 
(9,1,'2021-12-24 11:59:00',2,3,1.2,1,'采购入库'),
(10,1,'2021-12-24 11:59:10',2,3,0.2,1,'采购入库'),
(11,1,'2021-12-24 11:59:17',2,3,1.6,1,'采购入库'),
(12,1,'2021-12-24 11:59:56',2,1,0.5,1,'采购入库'),
(13,1,'2021-12-24 12:01:19',2,1,0.5,1,'采购入库'),
(14,1,'2021-12-24 12:01:47',2,2,2,1,'采购入库'),
(15,1,'2021-12-24 12:01:54',2,2,1,1,'采购入库'),
(16,1,'2021-12-24 12:06:32',1,5,1,1,'采购入库'),
(17,1,'2022-01-11 10:45:29',2,1,1,2,'销售出库'),
(18,1,'2022-01-13 10:27:54',2,5,300,1,'采购入库'),
(19,1,'2022-01-13 10:28:03',1,6,200,1,'采购入库'),
(20,1,'2022-01-13 10:29:04',2,5,20,2,'销售出库'),
(21,1,'2022-01-13 10:29:17',1,6,10,2,'销售出库'),
(22,1,'2022-01-14 10:42:26',1,1,200,1,'采购入库'),
(23,1,'2022-01-14 10:42:35',2,3,500,1,'采购入库'),
(24,1,'2022-01-26 17:53:44',2,2,1,2,'销售出库');

/*Data for the table `supplier` */

insert  into `supplier`(`UUID`,`NAME`,`ADDRESS`,`CONTACT`,`TELE`,`EMAIL`,`TYPE`) values 
(1,'淘宝','杭州','马云','110110','my@taobao.com','1'),
(2,'京东','北京','刘','120120','lqd@jd.com','2'),
(3,'天猫','杭州','马','130130','my@tianmao.com','1'),
(8,'中贸','广东深圳','A','12322223333','my@zm.com','1'),
(9,'中hai','深圳','Bc','12322223333','my@zm.com','1');

/*Data for the table `waybill` */

/*Data for the table `waybilldetail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
