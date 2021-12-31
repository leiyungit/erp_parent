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
(2,'sunwukong','sunwukong','孙悟空',1,'swk@itcast.cn','12345678','花果山水帘洞11-2','1969-10-01 00:00:00',2),
(3,'tangseng','tangseng','唐僧',1,'ts@itcast.cn','12345678','东?链筇菩∏?0-14','1949-10-01 00:00:00',2),
(4,'zhubajie','zhubajie','猪八戒',1,'zbj@itcast.cn','12345678','高老庄生态园2-3','1949-10-01 00:00:00',3),
(5,'shaheshang','shaheshang','沙和尚',1,'shs@itcast.cn','12345678','流沙河风景区4-4','1949-10-01 00:00:00',3),
(6,'bailongma','bailongma','白龙马',1,'blm@itcast.cn','12345678','西海家园4-9-1','1949-10-01 00:00:00',3),
(7,'test','5cbaca32e76bb49ca69657a9145d77ee','test',0,'test@qq.com','12345','','2021-12-17 00:00:00',1);

/*Data for the table `emp_role` */

/*Data for the table `goods` */

insert  into `goods`(`UUID`,`NAME`,`ORIGIN`,`PRODUCER`,`UNIT`,`INPRICE`,`OUTPRICE`,`GOODSTYPEUUID`) values 
(1,'水蜜桃','北京','北京水果之乡','斤',2.34,4.23,1),
(2,'大鸭梨','北京','北京水果之乡','斤',1.11,3.55,1),
(3,'猕猴桃','北京','北京水果之乡','斤',6.33,9.02,1),
(4,'甜面酱','北京','七必居','袋',4.11,6.33,2),
(5,'可比克','北京','北京山寨食品有限公司','袋',3.88,6.33,3),
(6,'好吃点','河北','河北好吃点食品公司','袋',4.22,5.21,3);

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
('301','采购订单查询','icon-sys','orders.html','300'),
('302','采购订单审核','icon-sys','orders.html?oper=doCheck','300'),
('303','采购订单确认','icon-sys','orders.html?oper=doStart','300'),
('304','采购订单入库','icon-sys','orders.html?oper=doInStore','300');

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`UUID`,`GOODSUUID`,`GOODSNAME`,`PRICE`,`NUM`,`MONEY`,`ENDTIME`,`ENDER`,`STOREUUID`,`STORENUM`,`STATE`,`ORDERSUUID`) values 
(1,1,'水蜜桃',2.34,1.00,2.34,'2021-12-24 12:01:19',1,2,1.00,2,1),
(2,2,'大鸭梨',1.11,3.00,3.33,'2021-12-24 12:01:54',1,2,3.00,2,1),
(3,5,'可比克',3.88,3.00,11.64,NULL,NULL,NULL,NULL,0,2),
(4,6,'好吃点',4.22,4.00,16.88,NULL,NULL,NULL,NULL,0,2),
(9,5,'可比克',3.88,3.00,11.64,'2021-12-24 12:06:32',1,1,1.00,1,5),
(10,6,'好吃点',4.22,4.00,16.88,NULL,NULL,NULL,NULL,0,5),
(13,3,'猕猴桃',6.33,3.00,18.99,'2021-12-24 11:59:17',1,2,3.00,2,8),
(14,5,'可比克',3.88,3.00,11.64,NULL,NULL,NULL,NULL,0,9),
(15,1,'水蜜桃',2.34,1.00,2.57,NULL,NULL,NULL,NULL,0,10),
(16,2,'大鸭梨',1.11,1.00,1.33,NULL,NULL,NULL,NULL,0,11),
(17,3,'猕猴桃',6.33,1.00,6.96,NULL,NULL,NULL,NULL,0,11),
(18,4,'甜面酱',4.11,2.00,8.22,NULL,NULL,NULL,NULL,0,11),
(19,1,'水蜜桃',2.34,1.11,2.60,NULL,NULL,NULL,NULL,0,12),
(20,1,'水蜜桃',2.34,1.11,2.60,NULL,NULL,NULL,NULL,0,13),
(21,1,'水蜜桃',2.34,1.11,2.60,NULL,NULL,NULL,0.00,0,14),
(22,1,'水蜜桃',2.34,100.00,234.00,NULL,NULL,NULL,NULL,0,15),
(23,1,'水蜜桃',2.34,200.00,468.00,NULL,NULL,NULL,NULL,0,16),
(24,2,'大鸭梨',1.11,1000.00,1110.00,NULL,NULL,NULL,NULL,0,17),
(25,3,'猕猴桃',6.33,50.00,316.50,NULL,NULL,NULL,NULL,0,18),
(26,3,'猕猴桃',6.33,1.00,6.33,NULL,NULL,NULL,NULL,0,19),
(27,3,'猕猴桃',6.33,1.00,6.33,NULL,NULL,NULL,NULL,0,20),
(28,3,'猕猴桃',6.33,20.00,126.60,NULL,NULL,NULL,NULL,0,21),
(29,4,'甜面酱',4.11,30.00,123.30,NULL,NULL,NULL,NULL,0,21);

/*Data for the table `orders` */

insert  into `orders`(`UUID`,`CREATETIME`,`CHECKTIME`,`STARTTIME`,`ENDTIME`,`TYPE`,`CREATER`,`CHECKER`,`STARTER`,`ENDER`,`SUPPLIERUUID`,`TOTALMONEY`,`STATE`,`WAYBILLSN`) values 
(1,'2021-12-21 15:39:45','2021-12-23 10:06:07','2021-12-24 11:47:50','2021-12-24 12:01:54',1,1,1,1,1,1,5.67,3,NULL),
(2,'2021-12-21 15:55:18','2021-12-24 12:02:41',NULL,NULL,1,7,1,NULL,NULL,3,28.52,1,NULL),
(3,'2021-12-21 16:18:04',NULL,NULL,NULL,1,7,3,NULL,NULL,3,15.10,0,NULL),
(4,'2021-12-21 16:23:28',NULL,NULL,NULL,1,7,4,NULL,NULL,3,15.10,0,NULL),
(5,'2021-12-21 16:33:27','2021-12-24 12:06:11','2021-12-24 12:06:17',NULL,1,1,1,1,NULL,3,28.52,2,NULL),
(8,'2021-12-21 17:16:11','2021-12-22 20:27:31','2021-12-22 20:43:47','2021-12-24 11:59:17',1,1,1,1,1,1,18.99,3,NULL),
(9,'2021-12-21 17:40:05',NULL,NULL,NULL,1,1,2,NULL,NULL,1,11.64,0,NULL),
(10,'2021-12-23 15:10:59',NULL,NULL,NULL,1,NULL,1,NULL,NULL,1,2.57,0,NULL),
(11,'2021-12-23 15:20:52',NULL,NULL,NULL,1,NULL,1,NULL,NULL,3,16.51,0,NULL),
(12,'2021-12-23 15:28:32',NULL,NULL,NULL,1,NULL,1,NULL,NULL,1,2.60,0,NULL),
(13,'2021-12-23 15:30:55','2021-12-24 11:47:24','2021-12-24 11:48:02',NULL,1,NULL,1,1,NULL,1,2.60,2,NULL),
(14,'2021-12-23 15:32:56','2021-12-24 11:47:20',NULL,NULL,1,NULL,1,NULL,NULL,1,2.60,1,NULL),
(15,'2021-12-24 17:32:30',NULL,NULL,NULL,1,NULL,1,NULL,NULL,2,234.00,0,NULL),
(16,'2021-12-24 17:39:13',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,468.00,0,NULL),
(17,'2021-12-24 17:43:24',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,1110.00,0,NULL),
(18,'2021-12-24 17:46:22',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,316.50,0,NULL),
(19,'2021-12-24 17:58:38',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,6.33,0,NULL),
(20,'2021-12-24 18:00:13',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,6.33,0,NULL),
(21,'2021-12-24 18:23:26',NULL,NULL,NULL,2,NULL,1,NULL,NULL,2,249.90,0,NULL);

/*Data for the table `returnorderdetail` */

/*Data for the table `returnorders` */

/*Data for the table `role` */

insert  into `role`(`UUID`,`NAME`) values 
(1,'超级管理员');

/*Data for the table `role_menu` */

/*Data for the table `store` */

insert  into `store`(`UUID`,`NAME`,`EMPUUID`) values 
(1,'冷藏库',NULL),
(2,'常温室',NULL);

/*Data for the table `storedetail` */

insert  into `storedetail`(`UUID`,`STOREUUID`,`GOODSUUID`,`NUM`) values 
(5,2,3,3.00),
(6,2,1,1.00),
(7,2,2,3.00),
(8,1,5,1.00);

/*Data for the table `storeoper` */

insert  into `storeoper`(`UUID`,`EMPUUID`,`OPERTIME`,`STOREUUID`,`GOODSUUID`,`NUM`,`TYPE`,`OPERDESC`) values 
(9,1,'2021-12-24 11:59:00',2,3,1.20,1,'采购入库'),
(10,1,'2021-12-24 11:59:10',2,3,0.20,1,'采购入库'),
(11,1,'2021-12-24 11:59:17',2,3,1.60,1,'采购入库'),
(12,1,'2021-12-24 11:59:56',2,1,0.50,1,'采购入库'),
(13,1,'2021-12-24 12:01:19',2,1,0.50,1,'采购入库'),
(14,1,'2021-12-24 12:01:47',2,2,2.00,1,'采购入库'),
(15,1,'2021-12-24 12:01:54',2,2,1.00,1,'采购入库'),
(16,1,'2021-12-24 12:06:32',1,5,1.00,1,'采购入库');

/*Data for the table `supplier` */

insert  into `supplier`(`UUID`,`NAME`,`ADDRESS`,`CONTACT`,`TELE`,`EMAIL`,`TYPE`) values 
(1,'淘宝','杭州','马云','110110','my@taobao.com','1'),
(2,'京东','北京','刘','120120','lqd@jd.com','2'),
(3,'天猫','杭州','马','130130','my@tianmao.com','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
