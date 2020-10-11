package com.story.tank.observer;

import com.story.tank.Tank;

/**
 * @Author story
 * @CreateTIme 2020/10/11
 **/
public class TankFireEvent {
    Tank tank;

    public Tank getSource(){
        return tank;
    }

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }
}
