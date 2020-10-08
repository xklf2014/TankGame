package com.story.tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Explode extends GameObject{
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth(), HEIGHT = ResourceMgr.explodes[0].getHeight();

    private int x, y;
    private boolean living = true;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        //new Audio("audio/explode.wav").play();
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) GameModel.getInstance().remove(this);

    }

}
