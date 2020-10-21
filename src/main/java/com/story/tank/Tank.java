package com.story.tank;

import com.story.tank.net.TankJoinMsg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private final int SPEED = PropertyMgr.getInt("tankSpeed");
    private boolean moving = false;
    private TankFrame tf;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth(), HEIGHT = ResourceMgr.goodTankU.getHeight();

    private boolean living = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
    private UUID id = UUID.randomUUID();

    public Tank(TankJoinMsg msg) {
        this.x = msg.x;
        this.y = msg.y;
        this.dir = msg.dir;
        this.group = msg.group;
        this.moving = msg.moving;
        this.id = msg.id;
    }

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        if (this.group == Group.BAD) {
            this.moving = true;
        }

        //this.SPEED = PropertyMgr.getInt("tankSpeed");

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Tank.WIDTH;
        rect.height = Tank.HEIGHT;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public void paint(Graphics g) {
        //if (!living) tf.enemies.remove(this);

        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(id.toString(), this.x, this.y - 20);
        g.drawString("live=" + living, x, y - 10);
        g.setColor(color);

        if (!living) {
            moving = false;
            Color cc = g.getColor();
            g.setColor(Color.WHITE);
            g.drawRect(x, y, WIDTH, HEIGHT);
            g.setColor(cc);
            return;
        }


        BufferedImage tankL = ResourceMgr.badTankL;
        BufferedImage tankU = ResourceMgr.badTankU;
        BufferedImage tankR = ResourceMgr.badTankR;
        BufferedImage tankD = ResourceMgr.badTankD;

        if (this.group == Group.GOOD) {
            tankL = ResourceMgr.goodTankL;
            tankU = ResourceMgr.goodTankU;
            tankR = ResourceMgr.goodTankR;
            tankD = ResourceMgr.goodTankD;
        }

      /*  Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(this.id.toString(),this.x,this.y);
        g.setColor(color);*/

        switch (dir) {
            case LEFT:
                g.drawImage(tankL, x, y, null);
                break;
            case UP:
                g.drawImage(tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(tankD, x, y, null);
                break;
        }

        move();

    }

    private void move() {
        if (!living) return;
        if (!moving) return;

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
        if (this.group == Group.BAD && random.nextInt(100) > 95) this.fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public UUID getId() {
        return id;
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2 - 1;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2 + 4;

        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
    }

    public void die() {
        this.living = false;
    }

}
