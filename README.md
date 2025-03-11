# my-waterfall-flow
图片瀑布流，展示图片 springboot mysql javascript

# 项目部署
参考了此项目：https://github.com/xieleihan/demo-Java-EE-Project.git 并在此项目上进行更改
## 第一步:克隆项目在本地
```text
git clone https://github.com/YinTao22/my-waterfall-flow.git
```
## 第二步:构建
在idea中，请重新构建项目。

# 数据库创建
```sql
create database demospringboot;
use demospringboot;
drop table if exists `user`;

-- 用户表 
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
select * from user;


--管理员表
CREATE TABLE admin (
    adminname VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

--图片表
CREATE TABLE image (
    id INT AUTO_INCREMENT PRIMARY KEY,
    imagename VARCHAR(255) NOT NULL,
    imagesurl VARCHAR(500) NOT NULL,
    height INT,
    width INT,
    imagealt VARCHAR(255)
);
