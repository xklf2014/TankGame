package com.story.tank.cor;

import com.story.tank.GameObject;
import com.story.tank.PropertyMgr;
import com.story.tank.ResourceMgr;
import com.story.tank.Tank;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        String str = PropertyMgr.getString("colliders");
        String[] colliders = str.split(",");
        for (int i = 0; i < colliders.length; i++) {
            try {
                Collider c = (Collider)Class.forName(colliders[i]).newInstance();
                this.colliders.add(c);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Collider c){
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return true;
    }

}
