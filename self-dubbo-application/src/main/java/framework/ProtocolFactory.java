package framework;

import protocol.dubbo.DubboProtocol;
import protocol.http.HttpProtocol;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ProtocolFactory {

    public static Protocol getProtocol(){
        // 工厂模式
        String name = System.getProperty("protocolName");
        switch (name){
            case "http":
                return new HttpProtocol();
            case "dubbospi":
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();
    }


    // META-INF/services/framework.Protocol里填写实现类protocol.dubbospi.DubboProtocol或者protocol.http.HttpProtocol
    public static Protocol getProtocolBySPI(){
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        return iterator.next();
    }

}
