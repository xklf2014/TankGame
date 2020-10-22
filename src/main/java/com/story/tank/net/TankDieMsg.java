package com.story.tank.net;

import com.story.tank.Bullet;
import com.story.tank.Tank;
import com.story.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Author story
 * @CreateTIme 2020/10/22
 **/
public class TankDieMsg extends Msg {
    UUID bulletId;
    UUID id;

    public TankDieMsg(UUID bulletId, UUID id) {
        this.bulletId = bulletId;
        this.id = id;
    }

    public TankDieMsg() {
    }

    @Override
    public void handle() {
        Tank t = TankFrame.INSTANCE.findByUUID(id);
        Bullet bullet = TankFrame.INSTANCE.findBulletByUUID(bulletId);

        if (bullet != null){
            bullet.die();
        }

        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId())){
            TankFrame.INSTANCE.getMainTank().die();
        }else {
            Tank tank = TankFrame.INSTANCE.findByUUID(id);
            if (tank != null ){
                tank.die();
            }
        }


    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;

        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(bulletId.getMostSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
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
        DataInputStream dis = null;
        ByteArrayInputStream bais = null;

        try {
            bais = new ByteArrayInputStream(bytes);
            dis = new DataInputStream(bais);
            this.bulletId = new UUID(dis.readLong(), dis.readLong());
            this.id = new UUID(dis.readLong(),dis.readLong());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (bais != null) {
                    bais.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDie;
    }

    @Override
    public String toString() {
        return "TankDieMsg{" +
                "bulletId=" + bulletId +
                ", id=" + id +
                '}';
    }
}
