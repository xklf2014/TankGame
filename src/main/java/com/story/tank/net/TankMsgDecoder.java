package com.story.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/18
 **/
public class TankMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 8) return;

        in.markReaderIndex();

        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();

        if (in.readableBytes() < length){
            in.resetReaderIndex();
            return;
        }

       byte[] bytes = new byte[length];
        in.readBytes(bytes);
        Msg msg = null;
        switch (msgType){
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankMovingMsg();
                break;
            case TankStop:
                msg = new TankStopMsg();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        out.add(msg);


    }
}
