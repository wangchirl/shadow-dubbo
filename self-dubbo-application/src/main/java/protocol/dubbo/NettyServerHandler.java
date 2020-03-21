package protocol.dubbo;

import framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import provider.LocalRegistry;

import java.lang.reflect.Method;

/**
 * 服务端处理类
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 强制转换
        Invocation invocation = (Invocation) msg;
        // 类似http请求处理
        // 1. 从本地注册中心获取实现类
        Class aClass = LocalRegistry.getImpl(invocation.getInterfaceName());
        // 2. 获取方法
        Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        // 3. 执行方法
        String result = (String) method.invoke(aClass.newInstance(), invocation.getParams());
        ctx.writeAndFlush("Dubbo - Netty:" + result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
