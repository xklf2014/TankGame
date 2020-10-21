package com.story.tank.net;

import com.story.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author story
 * @CreateTIme 2020/10/18
 **/
public class Client {
    public static final Client INSTANCE = new Client();
    private Channel channel = null;

    private Client(){}

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();

        try {

            ChannelFuture future = bootstrap.group(group).
                    channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
                    .connect("localhost",8888);

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        System.out.println("not connected!");
                    } else {
                        System.out.println("connected!");
                        channel = future.channel();
                    }
                }
            });

            future.sync();

            future.channel().closeFuture().sync();
            System.out.println("退出成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void send(Msg msg) {
        System.out.println("send"+msg);
        channel.writeAndFlush(msg);
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast(new TankMsgEncoder())
                .addLast(new TankMsgDecoder())
                .addLast(new ClientHandler());
    }
}

class ClientHandler extends SimpleChannelInboundHandler<Msg>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        ctx.writeAndFlush("------"+msg);
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
        //ctx.channel().read();
    }
}