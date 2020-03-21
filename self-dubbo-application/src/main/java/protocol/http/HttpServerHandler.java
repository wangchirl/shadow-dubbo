package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;
import provider.LocalRegistry;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp){
        // 处理请求 Invocation 对象
        try {
            // 1. 从请求中获取输入流 - 得到 Invocation 对象
            ServletInputStream inputStream = req.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();

            // 2. 从Invocation 中获取具体的参数
            // 2.1 从本地注册中获取接口实现类
            Class aClass = LocalRegistry.getImpl(invocation.getInterfaceName());
            // 2.2 从实现类中获取到具体的方法 [方法名称，方法参数]
            Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            // 2.3 执行方法 [实例对象，请求参数值]
            String result = (String) method.invoke(aClass.newInstance(), invocation.getParams());

            // 3. 返回结果
            IOUtils.write(result, resp.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

}
