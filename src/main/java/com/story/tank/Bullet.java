package com.story.tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Bullet extends GameObject{
    private final int SPEED;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;
    private Dir dir;
    private boolean living = true;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.SPEED = PropertyMgr.getInt("bulletSpeed");

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {

        if (!living) {
            GameModel.getInstance().remove(this);
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

        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;

        rect.x = this.x;
        rect.y = this.y;
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

    public Rectangle getRect() {
        return rect;
    }
}
