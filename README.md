# gulimall
谷粒商城服务端

### 1、下载镜像文件
docker pull mysql:5.7

### 2、创建实例并启动
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7

docker run -p 3306:3306 --name mysql -v  /mydata/mysql/log:/var/log/mysql  -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7

> 参数说明：
>
> -p 3306:3306   将容器3306端口映射到主机的3306端口
>
> --name mysql  给容器起个名字
>
> -v /mydata/mysql/log:/var/log/mysql \      将配置文件挂载到主机
>
> -v /mydata/mysql/data:/var/lib/mysql \    将日志文件挂在到主机
>
> -v /mydata/mysql/conf:/etc/mysql \	      将配置文件挂载到主机
>
> -e MYSQL_ROOT_PASSWORD=root \ 		初始化root用户密码
>
> -d 	后台运行

### 3、允许远程访问
```
#首先要进入容器内部
docker exec -it 容器名或容器ID

#进入mysql
mysql -u root -p
#授权
GRANT ALL PRIVILEGES ON *.* TO root@"%" IDENTIFIED BY "rw";
flush privileges;

#退出
exit
exit
#第一个exit退出mysql
#第二个exit退出容器的bash
```
### 4、redis
```
#拉去redis
docker pull redis
#运行
start a redis instance
$ docker run --name some-redis -d redis
start with persistent storage
$ docker run --name some-redis -d redis redis-server --appendonly yes
1、
mkdir -p /mydata/redis/conf
2、
touch /mydata/redis/conf/redis.conf
3、
docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data -v /mydata/redis/conf/redis.conf:/etc/redis/redis.conf -d redis redis-server /etc/redis/redis.conf
# dockerredis没有conf，默认挂在redis.conf是目录，需要自己创建文件
docker exec -it redis redis-cli
# redis默认配置没有持久化
vi redis
appendonly yes
```
### 5、开发环境配置
```
后端：
1、jdk:11
2、maven:
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\Application\apache-maven-3.6.3\bin\..
Java version: 11.0.8, vendor: Oracle Corporation, runtime: C:\Application\Java\jdk-11.0.8
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
3、IDEA:2020.1.3
4、Plugins:
lombok
mybatisx

前端：
vscode

```
### 6、项目结构创建与git
```
#创建空项目
#创建商品服务、订单服务、仓储服务、优惠卷服务、用户服务
#创建modul：
1、
spring-boot:
group:com.atguigu.gulimall
artifact:gulimall-product
description:谷粒商城-商品服务
package com.atguigu.gulimall.product
2、next:
web
spring cloud routing/openfeign
共同：
1）web、openfeign
2）包名com.atguigu.gulimall.xxx(product\order\ware\coupon\member)
```

### 7、数据库设计
```shell
#每个微服务之操作自己的数据库。
优惠/营销数据库：gulimall_sms
商品数据库：gulimall_pms
库存管理数据库：gulimall_wms
用户数据库：gulimall_ums
订单数据库：gulimall_oms

#docker容器自己自动启动
sudo docker update redis --restart=always
sudo docker update mysql --restart=always
```

## 8、人人开源搭建后台管理系统
p16
```
java后台管理系统
renren-fast
https://gitee.com/renrenio/renren-fast

前端分离的项目前端界面
renren-fast-vue
https://gitee.com/renrenio/renren-fast-vue
```
## 9、逆向工程搭建&使用
p17
```
https://gitee.com/renrenio/renren-generator
生成的代码main等
创建gulimall-common maven项目把公共引用的类放到这里
其他微服务引用gulimall-common

注释掉generator的模板
修改数据库、包名字、模块名、表前缀
```
## 10、配置测试微服务的CRUD
p18
```
1.整合mybatis plus
1).导入以来到common
2).配置mybatis plus
    1.配置数据源
        导入驱动
        在yml配置数据源
    2.配置其他
        配置MapperScan（接口）
        告诉mybatisplus配置映射文件位置
        自增
```
## 11、逆向生成所有微服务的CRUD
p19
```
```
## 12、分布式组件 -SpringCloud Alibaba简介
p20
```
注册中心：每个微服务注册到这里，如果服务调用服务先去注册中心看有哪些机器的商品服务，然后挑一个远程调用;Eureka
配置中心：比如10台机器上都有商品服务，修改配置希望再配置中心集中配置，实时修改;Spring Cloud Config
网关：前端请求进行统一的鉴权、过滤、路由，由网关找服务;Zuul,Hystrix
这里使用Spring Cloud Alibaba
```
