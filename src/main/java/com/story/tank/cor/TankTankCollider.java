package com.story.tank.cor;

import com.story.tank.Bullet;
import com.story.tank.Explode;
import com.story.tank.GameObject;
import com.story.tank.Tank;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class TankTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;

            if (t1.getRect().intersects(t2.getRect())){
                t1.randomDir();
                t2.randomDir();
            }
        }
       // return this;
    }

}
