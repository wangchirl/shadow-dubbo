package protocol.dubbo;

import framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyClient<T> {

    public NettyClientHandler clientHandler;

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public void start(String hostname, Integer port){
        start0(hostname,port);
    }

    private void start0(String hostname, Integer port){
        clientHandler = new NettyClientHandler();

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder",
                                new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass()
                                        .getClassLoader())));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        pipeline.addLast("handler", clientHandler);
                    }
                });
        try {
            bootstrap.connect(hostname, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //group.shutdownGracefully(); // 大坑，这里不能关闭
        }
    }


    public String send(String hostname, Integer port, Invocation invocation){
        if(clientHandler == null){
            start(hostname,port);
        }
        clientHandler.setInvocation(invocation);
        try {
            return (String) executorService.submit(clientHandler).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}
