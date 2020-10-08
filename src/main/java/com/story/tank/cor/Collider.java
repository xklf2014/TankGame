package com.story.tank.cor;

import com.story.tank.GameObject;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
