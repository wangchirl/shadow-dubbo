package registry;

import com.sun.org.apache.regexp.internal.RE;
import framework.URL;

import java.io.*;
import java.util.*;

/**
 * 远程服务注册
 */
public class RemoteRegistry {

    private static Map<String, List<URL>> REMOTE_REGISTRY = new HashMap<>();


    public static void registry(String interfaceName, URL url){
        // 先取出注册的，然后再加入进去
        List<URL> urls = REMOTE_REGISTRY.get(interfaceName);
        if(urls != null){
            urls.add(url);
        }else {
            urls = Collections.singletonList(url);
        }
        REMOTE_REGISTRY.put(interfaceName,urls);

        // 模拟服务注册中心 - 文件存储
        saveFile();
    }


    /**
     * 模拟 - 远程服务注册中心这里简单实现，服务注册后将信息保存到文件，这里从文件中读取
     * 根据接口名称获取注册列表，随机返回一个服务注册地址
     * @param interfaceName 接口名称
     * @return
     */
    public static URL random(String interfaceName){
        // 模拟 - 从文件读取注册的信息
        REMOTE_REGISTRY = getFile();

        List<URL> urls = REMOTE_REGISTRY.get(interfaceName);
        Random random = new Random();
        int index = random.nextInt(urls.size());
        return urls.get(index);
    }


    private static void saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(REMOTE_REGISTRY);
            oos.flush();
            oos.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            Map<String, List<URL>>  result = (Map<String, List<URL>>) ois.readObject();
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
