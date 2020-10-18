package com.story.tank.observer;

import com.story.tank.Tank;

import java.io.Serializable;

/**
 * @Author story
 * @CreateTIme 2020/10/11
 **/
public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank t = event.getSource();
        t.fire();
    }
}
