

## 环境配置

| 软件    | 版本    |
| ------- | ------- |
| eclipse | 2020-06 |
| maven   | 3.6.0   |
| mysql   | 5.7+    |
| JDK     | 1.8     |
| tomcat  |         |



## 项目结构说明

| 模块       | 说明         |
| ---------- | ------------ |
| erp_parent | maven父工程  |
| erp_entity | 实体类       |
| erp_dao    | 数据访问层   |
| erp_biz    | 业务层       |
| erp_web    | action和前端 |

### 前端：EasyUI

>html+ajax
>
>报表图表：HighCharts

#### HighCharts

* 中文在线文档：https://api.highcharts.com.cn/legacy/index.html

  使用步骤：

  >1、添加 highcharts.js 到ui文件夹
  >
  >​		引入exporting.js 导出的插件
  >
  >2、Pie charts
  >
  >​      Pie with legend 查看源码，复制到使用html和js
  >
  >

### 后端：SSH2

>struts2+spring+hibernate
>
>



## DB

> mysql/oracle  都可，当期使用mysql 
>
> 见文件：文档及数据库



### eclipse 主题修改

参考： https://blog.csdn.net/qq_43270074/article/details/83312506

[Eclipse Color主题下载](http://www.eclipsecolorthemes.org/?q=)   http://www.eclipsecolorthemes.org/?q=



eclipse 没有tomcat的问题

![1638503435871](C:\Users\v_yunylei\AppData\Roaming\Typora\typora-user-images\1638503435871.png)

 http://download.eclipse.org/releases/kepler 

展开 ”Web, XML, Java EE and OSGi Enterprise Development“选项，并且将下面四个选项打上勾：

Eclipse Java EE Developer Tools

Eclipse Java Web Developer Tools

Eclipse Web Developer Tools

Eclipse XML Editors and Tools
————————————————
版权声明：本文为CSDN博主「shuozhuo」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/mijichui2153/article/details/81185715



## 系统模块说明

### 采购管理

#### 业务流程

![image.png](https://upload-images.jianshu.io/upload_images/22934254-c3b9bb57882d4d15.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 采购入库

* 可以分批入库，每次入库手动输入入库数量

  ![image.png](https://upload-images.jianshu.io/upload_images/22934254-ee1c0e846193fbab.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 导出

​	 供应商和客户的导出，curd.js的toolbar抽取共用，实际使用的html可以清除和动态添加

​	采购单和销售单的导出，此单前端实现，加导出按钮