-- 建库
CREATE DATABASE  `erpdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- DROP DATABASE  `erpdb`

USE erpdb

/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/12/2 13:21:44                           */
/*==============================================================*/


/*==============================================================*/
/* Table: DEP                                                   */
/*==============================================================*/
CREATE TABLE DEP
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '部门名称',
   TELE                 VARCHAR(30) COMMENT '联系电话',
   PRIMARY KEY (UUID)
);

ALTER TABLE DEP COMMENT '部门';

/*==============================================================*/
/* Table: EMP                                                   */
/*==============================================================*/
CREATE TABLE EMP
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   USERNAME             VARCHAR(15) COMMENT '登陆名',
   PWD                  VARCHAR(32) COMMENT '登陆密码',
   NAME                 VARCHAR(28) COMMENT '真实姓名',
   GENDER               INT COMMENT '性别',
   EMAIL                VARCHAR(255) COMMENT '邮件地址',
   TELE                 VARCHAR(30) COMMENT '联系电话',
   ADDRESS              VARCHAR(255) COMMENT '联系地址',
   BIRTHDAY             DATETIME COMMENT '出生年月日',
   DEPUUID              BIGINT COMMENT '部门编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE EMP COMMENT '员工';

/*==============================================================*/
/* Table: EMP_ROLE                                              */
/*==============================================================*/
CREATE TABLE EMP_ROLE
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   EMPUUID              BIGINT COMMENT '员工编号',
   ROLEUUID             BIGINT COMMENT '角色编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE EMP_ROLE COMMENT '员工角色';

/*==============================================================*/
/* Table: GOODS                                                 */
/*==============================================================*/
CREATE TABLE GOODS
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   ORIGIN               VARCHAR(30) COMMENT '产地',
   PRODUCER             VARCHAR(30) COMMENT '厂家',
   UNIT                 VARCHAR(30) COMMENT '计量单位',
   INPRICE              NUMERIC(8,2) COMMENT '进货价格',
   OUTPRICE             NUMERIC(8,2) COMMENT '销售价格',
   GOODSTYPEUUID        BIGINT COMMENT '商品类型',
   PRIMARY KEY (UUID)
);

ALTER TABLE GOODS COMMENT '商品';

/*==============================================================*/
/* Table: GOODSTYPE                                             */
/*==============================================================*/
CREATE TABLE GOODSTYPE
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品类型编号',
   NAME                 VARCHAR(30) COMMENT '商品类型名称',
   PRIMARY KEY (UUID)
);

ALTER TABLE GOODSTYPE COMMENT '商品分类';

/*==============================================================*/
/* Table: INVENTORY                                             */
/*==============================================================*/
CREATE TABLE INVENTORY
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   GOODSUUID            BIGINT COMMENT '商品',
   STOREUUID            BIGINT COMMENT '仓库',
   NUM                  NUMERIC COMMENT '数量',
   TYPE                 CHAR(1) COMMENT '类型',
   CREATETIME           DATETIME COMMENT '登记日期',
   CHECKTIME            DATETIME COMMENT '审核日期',
   CREATER              BIGINT COMMENT '登记人',
   CHECKER              BIGINT COMMENT '审核人',
   STATE                CHAR(1) COMMENT '状态',
   REMARK               VARCHAR(200) COMMENT '备注',
   PRIMARY KEY (UUID)
);

ALTER TABLE INVENTORY COMMENT '盘盈盘亏';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
CREATE TABLE MENU
(
   MENUID               VARCHAR(20) NOT NULL COMMENT '菜单ID',
   MENUNAME             VARCHAR(30) COMMENT '菜单名称',
   ICON                 VARCHAR(20) COMMENT '图标',
   URL                  VARCHAR(255) COMMENT 'URL',
   PID                  VARCHAR(20) COMMENT '上级菜单ID',
   PRIMARY KEY (MENUID)
);

ALTER TABLE MENU COMMENT '菜单';

/*==============================================================*/
/* Table: ORDERDETAIL                                           */
/*==============================================================*/
CREATE TABLE ORDERDETAIL
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   GOODSNAME            VARCHAR(50) COMMENT '商品名称',
   PRICE                NUMERIC(10,2) COMMENT '价格',
   NUM                  NUMERIC COMMENT '数量',
   MONEY                NUMERIC(10,2) COMMENT '金额',
   ENDTIME              DATETIME COMMENT '结束日期',
   ENDER                BIGINT COMMENT '库管员',
   STOREUUID            BIGINT COMMENT '仓库编号',
   STATE                CHAR(1) COMMENT '采购：0=未入库，1=已入库  销售：0=未出库，1=已出库',
   ORDERSUUID           BIGINT COMMENT '订单编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE ORDERDETAIL COMMENT '订单明细';

/*==============================================================*/
/* Table: ORDERS                                                */
/*==============================================================*/
CREATE TABLE ORDERS
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   CREATETIME           DATETIME COMMENT '生成日期',
   CHECKTIME            DATETIME COMMENT '审核日期',
   STARTTIME            DATETIME COMMENT '确认日期',
   ENDTIME              DATETIME COMMENT '入库或出库日期',
   TYPE                 CHAR(1) COMMENT '1:采购 2:销售',
   CREATER              BIGINT COMMENT '下单员',
   CHECKER              BIGINT COMMENT '审核员',
   STARTER              BIGINT COMMENT '采购员',
   ENDER                BIGINT COMMENT '库管员',
   SUPPLIERUUID         BIGINT COMMENT '供应商或客户',
   TOTALMONEY           NUMERIC(10,2) COMMENT '合计金额',
   STATE                CHAR(1) COMMENT '采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',
   WAYBILLSN            VARCHAR(50) COMMENT '运单号',
   PRIMARY KEY (UUID)
);

ALTER TABLE ORDERS COMMENT '订单';

/*==============================================================*/
/* Table: RETURNORDERDETAIL                                     */
/*==============================================================*/
CREATE TABLE RETURNORDERDETAIL
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   GOODSNAME            VARCHAR(50) COMMENT '商品名称',
   PRICE                NUMERIC(10,2) COMMENT '价格',
   NUM                  NUMERIC COMMENT '数量',
   MONEY                NUMERIC(10,2) COMMENT '金额',
   ENDTIME              DATETIME COMMENT '结束日期',
   ENDER                BIGINT COMMENT '库管员',
   STOREUUID            BIGINT COMMENT '仓库编号',
   STATE                CHAR(1) COMMENT '状态',
   ORDERSUUID           VARCHAR(50) COMMENT '退货订单编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE RETURNORDERDETAIL COMMENT '退货订单明细';

/*==============================================================*/
/* Table: RETURNORDERS                                          */
/*==============================================================*/
CREATE TABLE RETURNORDERS
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   CREATETIME           DATETIME COMMENT '生成日期',
   CHECKTIME            DATETIME COMMENT '检查日期',
   ENDTIME              DATETIME COMMENT '结束日期',
   TYPE                 CHAR(1) COMMENT '订单类型',
   CREATER              BIGINT COMMENT '下单员',
   CHECKER              BIGINT COMMENT '审核员工编号',
   ENDER                BIGINT COMMENT '库管员',
   SUPPLIERUUID         BIGINT COMMENT '供应商及客户编号',
   TOTALMONEY           NUMERIC(10,2) COMMENT '合计金额',
   STATE                CHAR(1) COMMENT '订单状态',
   WAYBILLSN            VARCHAR(50) COMMENT '运单号',
   ORDERSUUID           BIGINT COMMENT '原订单编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE RETURNORDERS COMMENT '退货订单';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
CREATE TABLE ROLE
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   PRIMARY KEY (UUID)
);

ALTER TABLE ROLE COMMENT '角色';

/*==============================================================*/
/* Table: ROLE_MENU                                             */
/*==============================================================*/
CREATE TABLE ROLE_MENU
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   ROLEUUID             BIGINT,
   MENUUUID             VARCHAR(10),
   PRIMARY KEY (UUID)
);

ALTER TABLE ROLE_MENU COMMENT '角色菜单';

/*==============================================================*/
/* Table: STORE                                                 */
/*==============================================================*/
CREATE TABLE STORE
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   EMPUUID              BIGINT COMMENT '员工编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE STORE COMMENT '仓库';

/*==============================================================*/
/* Table: STOREDETAIL                                           */
/*==============================================================*/
CREATE TABLE STOREDETAIL
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   STOREUUID            BIGINT COMMENT '仓库编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   NUM                  NUMERIC COMMENT '数量',
   PRIMARY KEY (UUID)
);

ALTER TABLE STOREDETAIL COMMENT '仓库库存';

/*==============================================================*/
/* Table: STOREOPER                                             */
/*==============================================================*/
CREATE TABLE STOREOPER
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   EMPUUID              BIGINT COMMENT '操作员工编号',
   OPERTIME             DATETIME COMMENT '操作日期',
   STOREUUID            BIGINT COMMENT '仓库编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   NUM                  NUMERIC COMMENT '数量',
   TYPE                 CHAR(1) COMMENT '1：入库 2：出库',
   PRIMARY KEY (UUID)
);

ALTER TABLE STOREOPER COMMENT '仓库操作记录';

/*==============================================================*/
/* Table: SUPPLIER                                              */
/*==============================================================*/
CREATE TABLE SUPPLIER
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   ADDRESS              VARCHAR(100) COMMENT '联系地址',
   CONTACT              VARCHAR(30) COMMENT '联系人',
   TELE                 VARCHAR(30) COMMENT '联系电话',
   EMAIL                VARCHAR(100) COMMENT '邮件地址',
   TYPE                 CHAR(1) COMMENT '1：供应商 2：客户',
   PRIMARY KEY (UUID)
);

ALTER TABLE SUPPLIER COMMENT '供应商';

