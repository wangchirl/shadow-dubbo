package protocol.http;


import framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP 客户端
 */
public class HttpClient {

    /**
     * 发送请求
     * @param hostname 请求地址
     * @param port 请求端口
     * @param invocation 请求数据
     * @return
     */
    public String request(String hostname, Integer port, Invocation invocation){
        try {
            URL url = new URL("http",hostname,port,"/");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 1. 发送请求
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            // 2. 接收返回信息
            InputStream inputStream = httpURLConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
