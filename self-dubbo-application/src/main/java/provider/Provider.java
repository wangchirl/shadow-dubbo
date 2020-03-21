package provider;

import framework.Protocol;
import framework.ProtocolFactory;
import framework.URL;
import protocol.dubbo.DubboProtocol;
import protocol.dubbo.NettyServer;
import protocol.http.HttpProtocol;
import protocol.http.HttpServer;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import registry.RemoteRegistry;

public class Provider {
    public static void main(String[] args) {

        // 1. 本地注册
        // {服务名称：实现类}
        LocalRegistry.registry(HelloService.class.getName(),HelloServiceImpl.class);

        // 2.远程注册
        // {服务名称：List<URL>} 集群有多个服务IP
        URL url = new URL("localhost",8080);
        RemoteRegistry.registry(HelloService.class.getName(), url);

        // 3. 暴露服务（启动tomcat） - HTTP 协议
        //HttpServer httpServer = new HttpServer();
        //httpServer.start("localhost",8080);


        // Dubbo协议
        //NettyServer nettyServer = new NettyServer();
        //nettyServer.start("localhost",8080);

        // 4.协议优化 - 1
        //Protocol protocol = new HttpProtocol();
        //Protocol protocol = new DubboProtocol();
        //protocol.start(url);

        // 5.工厂模式优化 -DprotocolName=dubbo
        //Protocol protocol = ProtocolFactory.getProtocol();
        //protocol.start(url);

        // 6.JAVA SPI机制
        Protocol protocol = ProtocolFactory.getProtocolBySPI();
        protocol.start(url);
    }
}
