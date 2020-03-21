package provider;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地服务注册类
 */
public class LocalRegistry {

    private static Map<String, Class> LOCAL_REGISTRY = new HashMap<String, Class>();

    public static void registry(String interfaceName, Class implClass){
        LOCAL_REGISTRY.put(interfaceName, implClass);
    }

    public static Class getImpl(String interfaceName){
        return LOCAL_REGISTRY.get(interfaceName);
    }

}
