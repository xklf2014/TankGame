package com.story.tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Wall extends GameObject{
    public static final int WIDTH = ResourceMgr.wall.getWidth(), HEIGHT = ResourceMgr.wall.getHeight();

    private int x, y;
    private Rectangle rect = new Rectangle();

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.wall, x, y, null);
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
