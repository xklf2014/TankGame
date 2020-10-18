package com.story.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public abstract class GameObject implements Serializable {
    public int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
