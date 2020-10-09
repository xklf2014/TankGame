package com.story.tank.decorator;

import com.story.tank.GameObject;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/9
 **/
public class TailDecorator extends GODecorator {
    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(go.x,go.y,go.x + getWidth(),go.y + getHeight());
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
