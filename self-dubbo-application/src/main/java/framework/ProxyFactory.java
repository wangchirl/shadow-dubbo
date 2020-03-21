package framework;

import protocol.dubbo.DubboProtocol;
import protocol.dubbo.NettyClient;
import protocol.http.HttpClient;
import protocol.http.HttpProtocol;
import provider.api.HelloService;
import registry.RemoteRegistry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂
 */
public class ProxyFactory {

    public static <T>  T getProxy(Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // http协议
                //HttpClient httpClient = new HttpClient();
                Invocation invocation = new Invocation(
                        interfaceClass.getName(),
                        method.getName(),
                        method.getParameterTypes(),
                        args
                );
                // 1.修改前这里的地址是写死的，应该从注册中心获取
                // String result = httpClient.request("localhost", 8080, invocation);
                // 2.修改后
                URL url = RemoteRegistry.random(interfaceClass.getName());
                //String result = httpClient.request(url.getHostname(), url.getPort(), invocation);


                // Dubbo协议
                // NettyClient nettyClient = new NettyClient();
                // String result = nettyClient.send(url.getHostname(), url.getPort(), invocation);

                // 3.协议优化 - 1
                //Protocol protocol = new HttpProtocol();
                //Protocol protocol = new DubboProtocol();
                //String result = protocol.send(url, invocation);

                // 4. 工厂模式协议优化
                //Protocol protocol = ProtocolFactory.getProtocol();
                //String result = protocol.send(url, invocation);

                // 5. JAVA SPI 机制
                Protocol protocol = ProtocolFactory.getProtocolBySPI();
                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }

}
