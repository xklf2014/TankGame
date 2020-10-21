package com.story.tank.net;

/**
 * @Author story
 * @CreateTIme 2020/10/18
 **/
public abstract class Msg {
    public abstract void handle();
    public abstract byte[]  toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
