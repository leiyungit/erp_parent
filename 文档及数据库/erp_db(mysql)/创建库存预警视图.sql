-- 主数据
SELECT g.uuid,g.name, IFNULL(SUM(s.num),0) storenum FROM Goods g LEFT JOIN Storedetail s
  ON g.uuid=s.goodsuuid GROUP BY g.uuid,g.name
-- 采购待入库
 SELECT od.goodsuuid,SUM(od.num) innum FROM Orderdetail od, Orders o
  WHERE od.ordersuuid=o.uuid AND o.type=1 AND od.state<2 GROUP BY od.goodsuuid
-- 销售待出库
SELECT od.goodsuuid,SUM(od.num) outnum FROM Orderdetail od, Orders o
  WHERE od.ordersuuid=o.uuid AND o.type=2 AND od.state<2 GROUP BY od.goodsuuid

-- 创建视图
CREATE VIEW view_storealert AS
SELECT a.uuid,a.name,storenum,innum,outnum FROM(
  SELECT g.uuid,g.name, CAST(IFNULL(SUM(s.num),0)+IFNULL(g.beginstorenum,0)AS DECIMAL(18,2)) storenum FROM goods g LEFT JOIN storedetail s
  ON g.uuid=s.goodsuuid GROUP BY g.uuid,g.name
)a,(
  SELECT od.goodsuuid,CAST(SUM(od.num-IFNULL(od.storenum,0))AS DECIMAL(18,2)) innum FROM orderdetail od, orders o
  WHERE od.ordersuuid=o.uuid AND o.type=1 AND od.state<2 GROUP BY od.goodsuuid
)b,(
  SELECT od.goodsuuid,CAST(SUM(od.num-IFNULL(od.storenum,0))AS DECIMAL(18,2)) outnum FROM orderdetail od, orders o
  WHERE od.ordersuuid=o.uuid AND o.type=2 AND od.state<2 GROUP BY od.goodsuuid
)c
WHERE a.uuid=b.goodsuuid AND a.uuid=c.goodsuuid

-- 使用视图查询
SELECT * FROM view_storealert WHERE storenum<outnum-innum;