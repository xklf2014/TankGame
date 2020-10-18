package com.story.tank.observer;

import java.io.Serializable;

/**
 * @Author story
 * @CreateTIme 2020/10/11
 **/
public interface TankFireObserver extends Serializable {
    void actionOnFire(TankFireEvent event);
}
