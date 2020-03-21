package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HelloService;

import javax.swing.text.html.HTML;

public class Consumer {

    public static void main(String[] args) {
        // 1. 不使用代理
        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(
                HelloService.class.getName(),
                "sayHello",
                new Class[]{String.class},
                new Object[]{"shadow - "}
                );
        //String result = httpClient.request("localhost", 8080, invocation);
        //System.out.println(result);

        // 2. 使用代理优化
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("王钦"));
    }
}
