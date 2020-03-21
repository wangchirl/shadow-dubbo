package protocol.dubbo;


import framework.Invocation;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    // 请求的数据
    private Invocation invocation;

    // 返回的数据
    private Object result;

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        setResult(msg);
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(getInvocation());
        wait();
        return getResult();
    }


    // getter -setter

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
