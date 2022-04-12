# starcloud
## 项目总体简介

### 1.技术架构

1. Nacos,服务注册中心和配置中心，其中在Sentinel限流规则持久化规则推拉和链路追踪会涉及相关配置。

2. Gateway网关服务，提供请求转发和拦截过滤。

3. Spring Security Oauth2,用户认证&&鉴权。

4. Sentinel，服务降级&&限流和熔断。

5. Openfeign ,服务间的HTTP调用。

6. SkyWalking,服务链路追踪。

7. mysql ,redis

   其中关于SpringCloudAlibaba中的版本依赖，请参考官网，务必一致，本项目采取的版本如下

   ![image-20220412105820795](https://github.com/liuxiuxue/starcloud/blob/master/common-service/files/image-20220412105820795.png)

   <img src="https://github.com/liuxiuxue/starcloud/blob/master/common-service/files/image-20220412105844036.png" alt="image-20220412105844036"  />

### 2.各服务技术简介

#### 	1.GateWay网关

1. 对各个微服务模块的请求转发处理，目前只开启了动态路由，同时创建了一个基础的全局过滤器，来处理白名单和简单的请求转发。

2. 在网关中可以和Sentinel整合后，可以对微服务提供网关规则的限流，或者分组限流。目前未配置限流。

3. 网关层是微服务的入口，是可以做鉴权处理的。这里提供两种思路，一种直接在网关端认证通过后再分发到各个微服务，第二种是在各个微服务中

   分别进行鉴权。项目中选择了第二种方案，因为这种控制粒度会更精确，再就是网关本身是采用了Reactor模型，涉及到WebFlux的认证鉴权，调试
   起来有点头大，放弃了。

   

#### 2.Nacos服务注册中心和配置中心

1. 服务发现，服务注册以及统一的配置管理。
2. 可以与Sentinel整合，做限流规则持久化推送等，目前只支持持久化规则从Nacos推送到Sentinel，Sentinel直接推送到Nacos,这个没有直接实现，
   反正我是没有找到，需要改源码，一大堆的规则，改起来头都大，也不知道为啥官方不给直接提供一下，有兴趣的同学可以自己尝试一下。

#### 3.Sentinel服务降级 &&限流&&熔断

1. 可以直接与网关整合，根据网关限流规则进行一个整体服务的限流。
2. 可以与Openfeign整合，在微服务调用中，对服务进行限流。官方文档给了比较详细的说明，可以查阅一下。

#### 4.Openfeign 服务调用

1. 这个比较简单就是服务调用，采用代理的方式，可以在服务中无感知的调用其它微服务。
2. 项目中对请求头中的Header中Authorization 进行了一个拦截处理，将token向调用方游服务传递。
3. 提供一些请求压缩、也有一些降级处理等

#### 5.Spring Security Oauth2 

1. 主要提供了用户的认证鉴权，采用了JWT+RSA方式对token进行处理。
2. JWT TokenStore中关于rsa部分，直接是将公钥和私钥放到了redis,也可以通过文件，网上这种教程资源还蛮多的。

#### 6.SkyWalking 请求链路追踪

1. 配置了cluster:
     			selector: ${SW_CLUSTER:nacos}
2. storage:
     selector: ${SW_STORAGE:mysql}
3. mysql配置了时区，要不然会启动报错，同时mysql驱动包要放在apache-skywalking-apm-bin-es7\oap-libs包下
4. 启动成功会自动创建表，如果没有创建表成功，那就是出问题了，记得看日志排错。



### 3.服务流程跑通步骤

- mysql，项目中用到的数据sql会在common-service中提供文件，需要自行创建库如下，nacos:这个官网提供建表sql,

  user_auth:用户注册和认证的库，starcloud:业务库，目前就只有一张表。

- 启动redis中要首先把公钥和私钥生成，生成方式在oauth-service的Apptest中，这个代码要拷贝到别的项目中跑一下，

  要不然项目没法运行，因为JWT的配置会依赖这里。

- Nacos我改了用户密码，同时开启了持久规则到数据库，这个配置请参考一下官网，测试采用的是单机启动，各个服务

  配置中心的文件也在common-service中

- Sentinel也是单机启动

- 其余的好像没有啥了，目前只是大体流程跑通了，而且是没有 对分布式事务进行处理的，后续会加上，再就是对公共Bean的
  的处理考虑改为starter引入

成功启动后的测试步骤   1.获取token   2.请求其他服务带着token

![image-20220412114422945](https://github.com/liuxiuxue/starcloud/blob/master/common-service/files/image-20220412114422945.png)



![image-20220412114527454](https://github.com/liuxiuxue/starcloud/blob/master/common-service/files/image-20220412114527454.png)
