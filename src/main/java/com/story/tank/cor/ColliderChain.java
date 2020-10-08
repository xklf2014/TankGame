package com.story.tank.cor;

import com.story.tank.GameObject;
import com.story.tank.Tank;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class ColliderChain {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new BulletTankCollider());
        add(new TankTankCollider());
    }

    public void add(Collider c){
        colliders.add(c);
    }

    public void collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            colliders.get(i).collider(o1,o2);
        }
    }
}
