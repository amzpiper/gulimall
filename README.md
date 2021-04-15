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
1、jdk:1.8
2、maven:
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
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
```
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

## p16、人人开源搭建后台管理系统
```
java后台管理系统
renren-fast
https://gitee.com/renrenio/renren-fast

前端分离的项目前端界面
renren-fast-vue
https://gitee.com/renrenio/renren-fast-vue
```

## p17、逆向工程搭建&使用
```
https://gitee.com/renrenio/renren-generator
打开renren-generator
生成的代码main等
创建gulimall-common maven项目把公共引用的类放到这里
其他微服务引用gulimall-common

注释掉generator的模板
修改数据库、包名字、模块名、表前缀
```

## p18、配置测试微服务的CRUD
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
        配置自增
```

## p19、逆向生成所有微服务的CRUD
```
前面给商品服务生产了CRUD代码，这里生产其他的所有微服务的代码
```

## p20、分布式组件 -SpringCloud Alibaba简介
```
注册中心：每个微服务注册到这里，如果服务调用服务先去注册中心看有哪些机器的商品服务，然后挑一个远程调用;Eureka
配置中心：比如10台机器上都有商品服务，修改配置希望再配置中心集中配置，实时修改;Spring Cloud Config
网关：前端请求进行统一的鉴权、过滤、路由，由网关找服务;Zuul,Hystrix
这里使用Spring Cloud Alibaba：
https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md

https://github.com/alibaba/Nacos
Spring Cloud Alibaba-Nacos:注册中心
Spring Cloud Alibaba-Nacos:配置中心

https://spring.io/projects/spring-cloud-netflix
Spring Cloud Ribbon:负载均衡-NETFLIX
spring-cloud-loadbalancer
spring-cloud-loadbalancer 已经取代了 ribbon

https://spring.io/projects/spring-cloud-openfeign
Spring Cloud Feign:声明式HTTP客户端(调用远程服务)-OpenFeign

https://github.com/alibaba/Sentinel
Spring Cloud Alibaba-Sentinel:服务容错（限流、降级、熔断）

https://spring.io/projects/spring-cloud-gateway
Spring Cloud Gateway:API网关(webflux编程模式)

https://spring.io/projects/spring-cloud-sleuth
Spring Cloud Sleuth:调用链监控

https://github.com/seata/seata
Spring Cloud Alibaba-Seate:原Fescar，分布式解决方案
```

## p21、分布式组件 -SpringCloud Alibaba-Nacos-注册中心
```
https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md
https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-discovery-example/readme-zh.md
配置地址、应用名
1.引入 Nacos Discovery Starter。
2.在应用的 /src/main/resources/application.properties 配置文件中配置 Nacos Server 地址
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
配置yml:
  cloud:
    nacos:
      discovery:
        server-addr: 39.106.63.189:8848
  application:
    name: gulimall-coupon
3.使用 @EnableDiscoveryClient 注解开启服务注册与发现功能
```
## p22、分布式组件-SpringCloud-OpenFeign测试远程调用
```
从注册中心中获取对方服务所在位置。Feign是HTTP客户端
想要嗲用别的服务
1)引入openfeign
2)编写1个服务接口。告诉springcloud这个接口需要调用远程服务:声明接口的每一个方法都是调用哪一个服务的哪一个请求
3)开启远程调用功能 
@EnableFeignClients
完成了使用Nacos完成注册中心，并测试了远程调用功能。

问题：
引用本地其他项目找不到问题，配置xml
<groupId>com.atguigu.gulimall</groupId>
<version>0.0.1-SNAPSHOT</version>
<artifactId>gulimall-common</artifactId>
<packaging>jar</packaging>
maven安装install，安装到本地仓库后。可以引用成功了。
```
## p23、分布式组件-SpringCloud-Nacos配置中心-简单示例
```
https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md
https://github.com/alibaba/spring-cloud-alibaba/blob/master/spring-cloud-alibaba-examples/nacos-example/nacos-config-example/readme-zh.md
一、如何使用nacos最为配置中心
1.首先，修改 pom.xml 文件，引入 Nacos Config Starter。
 <dependency>
     <groupId>com.alibaba.cloud</groupId>
     <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 </dependency>
2.在应用的 /src/main/resources/bootstrap.properties 配置文件中配置 Nacos Config 元数据
#服务名称
spring.application.name=nacos-config-example
#配置中心地址
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
3.在配置列表中添加数据集gulimall-coupon.properties配置
添加当前应用名.properties的数据集(Data Id),在其中添加任何配置
4.动态获取配置:
添加 @RefreshScope 打开动态刷新功能,实现动态获取
使用 @Value 注解来将对应的配置注入到 SampleController 的 userName 和 age 字段
@RefreshScope
 class SampleController {
 	@Value("${user.name}")
 	String userName;

 	@Value("${user.age}")
 	int age;
 }
如果配置中都配置类相同的项，优先使用配置中心的项.
```
## p24、分布式组件-SpringCloud-Nacos配置中心-更多细节
```
二、Nacos配置中心-更多细节:
概念:
1.命名空间:配置隔离
默认是public，所有的新增的配置都在这里;
注意：
    1.比如：开发、生产、测试都有不同的配置。则需要创建更多的命名空间。
      默认使用public命名空间，可以在bootstrap中修改配置：spring.cloud.nacos.config.namespace=6b5d2efc-844c-423c-a0ca-cc259ddb8804
    2.每个微服务之间互相隔离，每个微服务都创建自己的命名空间，只加载自己命名空间下的配置

2.配置集:
一组相关不相关的配置项的集合。

3.配置集ID:
以前所有配置集合在文件中，id相当于文件名。
现在新增配置时的dataID叫配置集ID。类似配置文件名

4.配置分组*:
默认配置集都属于DEFAULT_GROUP组，组可以随意定制，可以随意切换。
创建配置时输入组。
```
## p25、分布式组件-SpringCloud-Nacos配置中心-加载多个配置集
```
三、同时加载多个配置集
当微服务不断壮大，要拆分为多个配置。
让微服务启动加载多个配置文件,
spring.cloud.nacos.config.ext-config[0].data-id=datasource.yml
spring.cloud.nacos.config.ext-config[0].group=DOUBLE_GROUP
#默认false
spring.cloud.nacos.config.ext-config[0].refresh=true
可以把application.yml注释掉。
1）微服务中的任何配置都可以放到配置中心中
2）只需要在bootstrap.properties中说明加载那些配置文件即可。
3）@Value、@ConfigurationProperties，以前springboot从配置文件中获取值的注解都能从配置中心中获取且优先
#开发期间在本地先，等上线再放在配置中心中.
```

## p26、分布式组件-SpringCloud-Gateway网关核心概念&原理
```
第一个需求：动态感知服务上下线，并路由到正确位置，拦截所有请求。
第二个需求：监控和鉴权、限流所有服务，防止重复开发，网关拦截请求，代理到服务。
springcloud-gateway是官方推出的第二代网关框架，取代zuul。
概念：
1.Route: The basic building block of the gateway. 
It is defined by an ID, a destination URI, a collection of predicates, and a collection of filters. 
A route is matched if the aggregate predicate is true.
断言判断是否路由到某一个地方。
2.Predicate: This is a Java 8 Function Predicate. 
The input type is a Spring Framework ServerWebExchange. 
This lets you match on anything from the HTTP request, such as headers or parameters.
断言是断言函数，匹配请求中的任何信息。
3.Filter: These are instances of GatewayFilter that have been constructed with a specific factory. 
Here, you can modify requests and responses before or after sending the downstream request.
网关过滤器：过滤请求和响应，信息都可以修改。
总结：请求到达网关，网关利用断言判定这次请求是否符合路由规则，符合就路由到指定地方，不过都要经过过滤器。
难点：定义路由规则，配置断言判定，使用那些filter？
```
## p27、分布式组件-SpringCloud-Gateway-创建&测试API网关
```
网关要把自己注册到注册中心在，也要发现其他服务的位置
/**
 * 1、开启网关服务注册发现,配置nacos注册中心地址
 * @EnableDiscoveryClient
 * 2、排除数据库自动配置
 * exclude = {DataSourceAutoConfiguration.class}
 */
localhost:88/?url=baidu
```
## p28、前端基础
```
前端工具：
vscod
js
webpack
npm
```

## p45、商品服务-API-三级分类-查询-递归树形结构数据获取
```
CategoryServiceImpl
```

## p46、商品服务-API-三级分类-配置网络路由与路径重写
```
人人开源路由规则
product/category会变成product-category
sys-role对应目录为renren-fast-vue\src\views\modules\sys

nacos还有问题，是因为配置中心没有配置

增加路由规则
```
## p47、商品服务-API-三级分类-网关统一-配置跨域
```
跨域：
指浏览器不能执行其他网站的脚本。它是由浏览器的同源策略造成的，是浏览器对javascript施加的安全限制。
同源策略：
协议、域名、端口其中一个不同就跨域。
OPTION请求预见。
解决跨域：
1、使用ngnix配置为同一域
静态请求：http:nginx/xx
动态请求：http:nginx/api/xx
2、配置每次请求允许跨域
2.1、添加响应头
Access-Control-Allow-Orign
Access-Control-Allow-Methods(我们用这个)
Access-Control-Allow-Credentials
Access-Control-Allow-Headers
卸载网关中统一配置跨域
```
## p48、商品服务-API-三级分类-查询树形三级分类数据
```
在网关中配置商品服务的路由配置
- id: product_route
  uri: lb://gulimall-product
  predicates:
    - Path=/api/product/**
  filters:
    - RewritePath=/api(?<segment>/?.*), /$\{segment}
配置商品服务的注册发现
重启网关
报异常：
{
"msg": "invalid token",
"code": 401
}
因为请求被/api/**先拦截了，需要改变路由顺序，精确路由放高优先级
```
## p50、商品服务-API-三级分类-删除-逻辑删除
```
逻辑删除步骤：
文档：
https://baomidou.com/guide/logic-delete.html
1.配置全局逻辑删除规则(可省略)
mybatis-plus:
  global-config:
    db-config:
      id-type: auto
      logic-delete-value: 1 # 逻辑已删除值(默认为 1)
      logic-not-delete-value: 0 # 逻辑未删除值(默认为 0)
2.配置全局组件(可选)(可省略)
3.给实体类Bean加注
@TableLogic
private Integer deleted;
```
