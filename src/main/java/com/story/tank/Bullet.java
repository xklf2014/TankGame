package com.story.tank;

import com.story.tank.net.Client;
import com.story.tank.net.TankDieMsg;

import java.awt.*;
import java.util.UUID;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Bullet {
    private final int SPEED;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;
    private Dir dir;
    private boolean living = true;
    TankFrame tf = null;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    private UUID id = UUID.randomUUID();
    private UUID playerId;

    public Bullet(UUID playerId,int x, int y, Dir dir, Group group, TankFrame tf) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.SPEED = PropertyMgr.getInt("bulletSpeed");

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
    }

    public void paint(Graphics g) {

        if (!living) {
            tf.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }

        move();
    }

    private void move() {

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        rect.x = this.x;
        rect.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;

    }


    public void collideWith(Tank tank) {
        if (this.playerId.equals(tank.getId()))return;

        if (this.rect.intersects(tank.rect) && this.living && tank.isLiving()) {
            tank.die();
            this.die();
            //int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            //int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            //tf.explodes.add(new Explode(eX, eY, this.tf));

            Client.INSTANCE.send(new TankDieMsg(this.id,tank.getId()));
        }
    }

    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
