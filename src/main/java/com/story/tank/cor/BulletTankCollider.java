package com.story.tank.cor;

import com.story.tank.*;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
           Bullet b =  (Bullet)o1;
           Tank t = (Tank)o2;
            if (colliderWith(b,t)){
                return false;
            }
        }else if (o2 instanceof Bullet && o1 instanceof Tank){
             collide(o2,o1);
        }
        return true;
    }

    private boolean colliderWith(Bullet b,Tank t){
        if (b.getGroup() == t.getGroup()) return false;

        if (b.getRect().intersects(t.getRect())) {
            t.die();
            b.die();
            int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            new Explode(eX, eY);
            return true;
        }
        return false;
    }
}
