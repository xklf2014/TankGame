package com.story.tank.decorator;

import com.story.tank.GameObject;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/9
 **/
public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
