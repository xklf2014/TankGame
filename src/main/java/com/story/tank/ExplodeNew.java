package com.story.tank;

import com.story.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/7
 **/
public class ExplodeNew extends BaseExplode {
    public static final int WIDTH = ResourceMgr.explodes_new[0].getWidth(), HEIGHT = ResourceMgr.explodes_new[0].getHeight();

    private int x, y;
    private boolean living = true;
    TankFrame tf = null;
    private int step = 0;

    public ExplodeNew(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        //new Audio("audio/explode.wav").play();
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explodes_new[step++], x, y, null);
        if (step >= ResourceMgr.explodes_new.length) this.tf.explodes.remove(this);

    }
}
