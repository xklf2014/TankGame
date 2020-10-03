package com.story.tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Bullet {
    private static final int SPEED = 10;
    private static final int WIDTH = 30, HEIGHT = 30;

    private int x, y;
    private Dir dir;
    private boolean living = true;
    TankFrame tf = null;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {

        if (!living) {
            tf.bullets.remove(this);
        }

        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
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

    }
}
