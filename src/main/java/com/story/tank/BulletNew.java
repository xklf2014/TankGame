package com.story.tank;

import com.story.abstractfactory.BaseBullet;
import com.story.abstractfactory.BaseTank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class BulletNew extends BaseBullet {
    private final int SPEED;
    public static final int WIDTH = ResourceMgr.bulletD_new.getWidth(), HEIGHT = ResourceMgr.bulletD_new.getHeight();

    private int x, y;
    private Dir dir;
    private boolean living = true;
    TankFrame tf = null;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();

    public BulletNew(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        this.SPEED = PropertyMgr.getInt("bulletSpeed");

        rect.x = this.x;
        rect.y = this.y;
        rect.width = BulletNew.WIDTH;
        rect.height = BulletNew.HEIGHT;

        this.tf.bullets.add(this);
    }

    @Override
    public void paint(Graphics g) {

        if (!living) {
            tf.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL_new, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU_new, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR_new, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD_new, x, y, null);
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


    public void collideWith(Tank tank) {

        if (this.group == tank.getGroup()) return;

        if (this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(tf.gf.createExplode(eX, eY, this.tf));
        }
    }

    private void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
