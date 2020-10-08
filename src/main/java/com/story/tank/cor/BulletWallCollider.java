package com.story.tank.cor;

import com.story.tank.Bullet;
import com.story.tank.GameObject;
import com.story.tank.Tank;
import com.story.tank.Wall;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class BulletWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b1 = (Bullet) o1;
            Wall t2 = (Wall) o2;

            if (b1.getRect().intersects(t2.getRect())){
                b1.die();
            }

        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;
    }
}
