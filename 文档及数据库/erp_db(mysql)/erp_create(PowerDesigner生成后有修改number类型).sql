-- 建库
CREATE DATABASE  `erpdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- DROP DATABASE  `erpdb`

USE erpdb

PS: linux环境中，mysql5.7+ 表名区分大小写，需要修改配置文件，由于已经存在其他库表，导致无法添加配置
/*==============================================================*/
/* DBMS name:      MySQL 5.7                                    */
/* Created on:     2021/12/2 13:21:44                           */
/*==============================================================*/


/*==============================================================*/
/* Table: DEP                                                   */
/*==============================================================*/
CREATE TABLE dep
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '部门名称',
   TELE                 VARCHAR(30) COMMENT '联系电话',
   PRIMARY KEY (UUID)
);

ALTER TABLE dep COMMENT '部门';

/*==============================================================*/
/* Table: EMP                                                   */
/*==============================================================*/
CREATE TABLE emp
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

ALTER TABLE emp COMMENT '员工';

/*==============================================================*/
/* Table: EMP_ROLE                                              */
/*==============================================================*/
CREATE TABLE emp_role
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   EMPUUID              BIGINT COMMENT '员工编号',
   ROLEUUID             BIGINT COMMENT '角色编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE emp_role COMMENT '员工角色';

/*==============================================================*/
/* Table: GOODS                                                 */
/*==============================================================*/
CREATE TABLE goods
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

ALTER TABLE goods COMMENT '商品';

/*==============================================================*/
/* Table: GOODSTYPE                                             */
/*==============================================================*/
CREATE TABLE goodstype
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品类型编号',
   NAME                 VARCHAR(30) COMMENT '商品类型名称',
   PRIMARY KEY (UUID)
);

ALTER TABLE goodstype COMMENT '商品分类';

/*==============================================================*/
/* Table: INVENTORY                                             */
/*==============================================================*/
CREATE TABLE inventory
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

ALTER TABLE inventory COMMENT '盘盈盘亏';

/*==============================================================*/
/* Table: MENU                                                  */
/*==============================================================*/
CREATE TABLE menu
(
   MENUID               VARCHAR(20) NOT NULL COMMENT '菜单ID',
   MENUNAME             VARCHAR(30) COMMENT '菜单名称',
   ICON                 VARCHAR(20) COMMENT '图标',
   URL                  VARCHAR(255) COMMENT 'URL',
   PID                  VARCHAR(20) COMMENT '上级菜单ID',
   PRIMARY KEY (MENUID)
);

ALTER TABLE menu COMMENT '菜单';

/*==============================================================*/
/* Table: ORDERDETAIL                                           */
/*==============================================================*/
CREATE TABLE orderdetail
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   GOODSNAME            VARCHAR(50) COMMENT '商品名称',
   PRICE                NUMERIC(10,2) COMMENT '价格',
   NUM                  NUMERIC(10,2) COMMENT '数量',
   MONEY                NUMERIC(10,2) COMMENT '金额',
   ENDTIME              DATETIME COMMENT '结束日期',
   ENDER                BIGINT COMMENT '库管员',
   STOREUUID            BIGINT COMMENT '仓库编号',
   STORENUM             NUMERIC(10,2) COMMENT '入库/出库数量',
   STATE                INT(1) COMMENT '采购：0=未入库，1=部分入库，2=已入库  销售：0=未出库，1=部分出库，2=已出库',
   ORDERSUUID           BIGINT COMMENT '订单编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE orderdetail COMMENT '订单明细';

/*==============================================================*/
/* Table: ORDERS                                                */
/*==============================================================*/
CREATE TABLE orders
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NOTEDATE             DATE NOT NULL COMMENT '单据日期',
   CREATETIME           DATETIME COMMENT '生成日期',
   CHECKTIME            DATETIME COMMENT '审核日期',
   STARTTIME            DATETIME COMMENT '确认日期',
   ENDTIME              DATETIME COMMENT '入库或出库日期',
   TYPE                 INT(1) COMMENT '1:采购 2:销售',
   CREATER              BIGINT COMMENT '下单员',
   CHECKER              BIGINT COMMENT '审核员',
   STARTER              BIGINT COMMENT '采购员',
   ENDER                BIGINT COMMENT '库管员',
   SUPPLIERUUID         BIGINT COMMENT '供应商或客户',
   TOTALMONEY           NUMERIC(10,2) COMMENT '合计金额',
   STATE                INT(2) COMMENT '采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',
   WAYBILLSN            VARCHAR(50) COMMENT '运单号',
   PRIMARY KEY (UUID)
);

ALTER TABLE orders COMMENT '订单';

/*==============================================================*/
/* Table: RETURNORDERDETAIL                                     */
/*==============================================================*/
CREATE TABLE returnorderdetail
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

ALTER TABLE returnorderdetail COMMENT '退货订单明细';

/*==============================================================*/
/* Table: RETURNORDERS                                          */
/*==============================================================*/
CREATE TABLE returnorders
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NOTEDATE             DATE NOT NULL COMMENT '单据日期',
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

ALTER TABLE returnorders COMMENT '退货订单';

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
CREATE TABLE role
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   PRIMARY KEY (UUID)
);

ALTER TABLE role COMMENT '角色';

/*==============================================================*/
/* Table: ROLE_MENU                                             */
/*==============================================================*/
CREATE TABLE role_menu
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   ROLEUUID             BIGINT,
   MENUUUID             VARCHAR(10),
   PRIMARY KEY (UUID)
);

ALTER TABLE role_menu COMMENT '角色菜单';

/*==============================================================*/
/* Table: STORE                                                 */
/*==============================================================*/
CREATE TABLE store
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   NAME                 VARCHAR(30) COMMENT '名称',
   EMPUUID              BIGINT COMMENT '员工编号',
   PRIMARY KEY (UUID)
);

ALTER TABLE store COMMENT '仓库';

/*==============================================================*/
/* Table: STOREDETAIL      添加初始库存，库存数需与库存关联                                     */
/*==============================================================*/
CREATE TABLE storedetail
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   STOREUUID            BIGINT COMMENT '仓库编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   NUM                  NUMERIC(10,2) COMMENT '数量',
   PRIMARY KEY (UUID)
);

ALTER TABLE storedetail COMMENT '仓库库存';

/*==============================================================*/
/* Table: STOREOPER                                             */
/*==============================================================*/
CREATE TABLE storeoper
(
   UUID                 BIGINT NOT NULL AUTO_INCREMENT COMMENT '编号',
   EMPUUID              BIGINT COMMENT '操作员工编号',
   OPERTIME             DATETIME COMMENT '操作日期',
   STOREUUID            BIGINT COMMENT '仓库编号',
   GOODSUUID            BIGINT COMMENT '商品编号',
   NUM                  NUMERIC(10,2) COMMENT '数量',
   TYPE                 INT(1) COMMENT '1：入库 2：出库',
   OPERDESC             VARCHAR(32) COMMENT '操作描述',
   PRIMARY KEY (UUID)
);

ALTER TABLE storeoper COMMENT '仓库操作记录';

/*==============================================================*/
/* Table: SUPPLIER                                              */
/*==============================================================*/
CREATE TABLE supplier
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

ALTER TABLE supplier COMMENT '供应商';

