package com.story.tank.cor;

import com.story.tank.Bullet;
import com.story.tank.Explode;
import com.story.tank.GameObject;
import com.story.tank.Tank;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class BulletTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
           Bullet b =  (Bullet)o1;
           Tank t = (Tank)o2;
            colliderWith(b,t);
        }else if (o2 instanceof Bullet && o1 instanceof Tank){
            collider(o2,o1);
        }
        //return this;
    }

    private void colliderWith(Bullet b,Tank t){
        if (b.getGroup() == t.getGroup()) return;

        if (b.getRect().intersects(t.getRect())) {
            t.die();
            b.die();
            int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            t.getGm().add(new Explode(eX, eY, t.getGm()));
        }
    }
}