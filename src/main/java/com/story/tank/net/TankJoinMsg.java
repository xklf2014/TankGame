package com.story.tank.net;

import com.story.tank.Dir;
import com.story.tank.Group;
import com.story.tank.Tank;
import com.story.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Author story
 * @CreateTIme 2020/10/18
 **/
public class TankJoinMsg extends Msg {
    public int x, y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    public TankJoinMsg() {
    }

    public TankJoinMsg(Tank tank){
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.id = tank.getId();
        this.moving = tank.isMoving();
    }
    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoin;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    @Override
    public void handle(){
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId())
                || TankFrame.INSTANCE.findByUUID(this.id) != null) return;
        System.out.println("--------------"+this);
        Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);

        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }
}
