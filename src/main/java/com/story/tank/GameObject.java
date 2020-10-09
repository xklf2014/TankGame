package com.story.tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public abstract class GameObject {
    public int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
