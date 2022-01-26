CREATE DATABASE redsun DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use redsun;

DROP TABLE IF EXISTS waybilldetail;

DROP TABLE IF EXISTS waybill;

/*==============================================================*/
/* Table: WAYBILLDETAIL                                         */
/*==============================================================*/
CREATE TABLE `waybill` (
  `sn` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '运单号',
  `userid` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `toaddress` VARCHAR(100) DEFAULT NULL COMMENT '收货地址',
  `addressee` VARCHAR(100) DEFAULT NULL COMMENT '收货人',
  `tele` VARCHAR(100) DEFAULT NULL COMMENT '收件人电话',
  `info` VARCHAR(2000) DEFAULT NULL COMMENT '运单详情',
  `state` VARCHAR(1) DEFAULT NULL COMMENT '运单状态',
  PRIMARY KEY (`sn`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `waybilldetail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sn` BIGINT(20) DEFAULT NULL COMMENT '运单号',
  `exedate` VARCHAR(10) DEFAULT NULL COMMENT '执行日期',
  `exetime` VARCHAR(10) DEFAULT NULL COMMENT '执行时间',
  `info` VARCHAR(100) DEFAULT NULL COMMENT '执行信息',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

ALTER TABLE waybill COMMENT '运单';