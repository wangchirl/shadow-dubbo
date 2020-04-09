### SpringBoot 集成 Dubbo的使用

1. 引入starter
2. 引入dubbo其他依赖（这里使用的是alibaba的Dubbo依赖）
3. @Service 暴露服务
4. @Refrence 订阅服务
    @EnbaleDubbo 开启Dubbo
5. TO（实体）必须实现序列化接口
6. 使用zookeeper作为注册中心，必须先启动ZK

### [Dubbo详细配置说明](http://dubbo.apache.org/zh-cn/docs/user/configuration/xml.html) 

### [特性](http://dubbo.apache.org/zh-cn/docs/user/preface/architecture.html)
1. 注册中心宕机后服务可用，本地缓存
2. 支持服务直连 url 属性
3. monitor宕机不影响服务可用性


### 部分属性的说明
1. @Service 暴露服务
    - version 版本号-灰度发布
    - weight 权重
    - retries 重试次数
    - timeout 超时时间
2. @Reference 订阅服务 
    - version 版本号-灰度发布
    - url 服务直连
    - timeout 超时时间
    - retries 重试次数
3. @EnableDubbo 开启 Dubbo


### 配置优先级说明
1. 方法级优先，接口级次之，全局配置再次之。
2. 如果级别一样，则消费方优先，提供方次之





### Dubbo 的 xml 使用
1. dubbo:service
2. dubbo:reference
3. dubbo:protocol

### 自己实现 http 和 基于 netty 的 dubbo 协议 
1. http的实现
2. netty的实现

### JAVA SPI 及 Dubbo SPI
1. Java SPI 机制
    - 不能取到指定的实现类
    - 没有 AOP 和 IOC 的功能
2. Dubbo SPI 机制
    - 可以获取指定的实现类
    - 支持 AOP IOC
        - URL
        - @SPI
        - @Adaptive
        
