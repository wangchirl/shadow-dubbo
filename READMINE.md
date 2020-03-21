### Dubbo 的 xml 使用
1. dubbo:service
2. dubbo:refrence
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
        