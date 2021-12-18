


-- 部门表--
INSERT INTO dep VALUES (1,'管理员组', '000000');
INSERT INTO dep VALUES (2,'总裁办', '111111');
INSERT INTO dep VALUES (3,'采购部', '222222');
INSERT INTO dep VALUES (4,'销售部', '333333');
INSERT INTO dep VALUES (5,'公关部', '444444');
INSERT INTO dep VALUES (6,'行政部', '555555');
INSERT INTO dep VALUES (7,'人事部', '555555');
INSERT INTO dep VALUES (8,'运输部', '444444');
INSERT INTO dep VALUES (9,'党办', '555555');
INSERT INTO dep VALUES (10,'工会', '555555');
INSERT INTO dep VALUES (11,'仓储部', '555555');
INSERT INTO dep VALUES (12,'客服部', '555555');
INSERT INTO dep VALUES (13,'财务部', '555555');
INSERT INTO dep VALUES (13,'运营部', '555555');


-- 角色表--
INSERT INTO role VALUES (1,'超级管理员');


-- 用户表--
INSERT INTO emp VALUES (1,'admin','admin','超级管理员',1,'admin@itcast.cn','12345678','建材城西路中腾商务大厦','1949-10-01',1);
INSERT INTO emp VALUES (2,'sunwukong','sunwukong','孙悟空',1,'swk@itcast.cn','12345678','花果山水帘洞11-2','1949-10-01',2);
INSERT INTO emp VALUES (3,'tangseng','tangseng','唐僧',1,'ts@itcast.cn','12345678','东?链筇菩∏?0-14','1949-10-01',2);
INSERT INTO emp VALUES (4,'zhubajie','zhubajie','猪八戒',1,'zbj@itcast.cn','12345678','高老庄生态园2-3','1949-10-01',3);
INSERT INTO emp VALUES (5,'shaheshang','shaheshang','沙和尚',1,'shs@itcast.cn','12345678','流沙河风景区4-4','1949-10-01',3);
INSERT INTO emp VALUES (6,'bailongma','bailongma','白龙马',1,'blm@itcast.cn','12345678','西海家园4-9-1','1949-10-01',3);

-- 商品类型表--
INSERT INTO goodstype VALUES (1,'水果');
INSERT INTO goodstype VALUES (2,'调味品');
INSERT INTO goodstype VALUES (3,'儿童食品');

-- 商品表--
INSERT INTO goods VALUES (1,'水蜜桃','北京','北京水果之乡','斤',2.34,4.23,1);
INSERT INTO goods VALUES (2,'大鸭梨','北京','北京水果之乡','斤',1.11,3.55,1);
INSERT INTO goods VALUES (3,'猕猴桃','北京','北京水果之乡','斤',6.33,9.02,1);
INSERT INTO goods VALUES (4,'甜面酱','北京','七必居','袋',4.11,6.33,2);
INSERT INTO goods VALUES (5,'可比克','北京','北京山寨食品有限公司','袋',3.88,6.33,3);
INSERT INTO goods VALUES (6,'好吃点','河北','河北好吃点食品公司','袋',4.22,5.21,3);

