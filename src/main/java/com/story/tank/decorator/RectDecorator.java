package com.story.tank.decorator;

import com.story.tank.GameObject;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/9
 **/
public class RectDecorator extends GODecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;
        go.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(go.x,y,getWidth()+2,getHeight()+2);
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
