package framework;

public interface Protocol {

    // 启动协议方法
    void start(URL url);

    // 发送数据方法
    String send(URL url,Invocation invocation);


}
