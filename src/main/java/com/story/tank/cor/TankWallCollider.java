package com.story.tank.cor;

import com.story.tank.GameObject;
import com.story.tank.Tank;
import com.story.tank.Wall;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank t1 = (Tank) o1;
            Wall t2 = (Wall) o2;

            if (t1.getRect().intersects(t2.getRect())){
                t1.randomDir();
            }

        }else if (o1 instanceof Wall && o2 instanceof Tank){
            collide(o2,o1);
        }
        return true;
    }
}
