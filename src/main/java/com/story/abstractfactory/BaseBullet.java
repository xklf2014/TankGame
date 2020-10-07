package com.story.abstractfactory;

import com.story.tank.Tank;

import java.awt.*;

/**
 * @Author story
 * @CreateTIme 2020/10/7
 **/
public abstract class BaseBullet {
    public abstract void paint(Graphics graphics);

    public abstract void collideWith(Tank tank);
}
