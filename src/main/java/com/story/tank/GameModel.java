package com.story.tank;

import com.story.tank.cor.BulletTankCollider;
import com.story.tank.cor.Collider;
import com.story.tank.cor.ColliderChain;
import com.story.tank.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class GameModel {
    Tank myTank = new Tank(PropertyMgr.getInt("myTankLocX"), PropertyMgr.getInt("myTankLocY"), getDir(), Group.GOOD, this);
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
    private List<GameObject> gameObjects = new ArrayList<>();
    ColliderChain colliderChain = new ColliderChain();


    public GameModel() {
        int initTankCount = PropertyMgr.getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }

        for (int i = 0; i < 20; i++) {
            add(new Wall(100+ i * Wall.WIDTH,300,this));
        }
    }

    public void add(GameObject go) {
        this.gameObjects.add(go);
    }

    public void remove(GameObject go) {
        this.gameObjects.remove(go);
    }


    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        //g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        //g.drawString("敌军的数量:" + enemies.size(), 10, 80);
        g.setColor(color);

        myTank.paint(g);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                colliderChain.collide(o1,o2);

            }
        }

        /*for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));
            }
        }*/


    }

    public Tank getMainTank() {
        return myTank;
    }

    public Dir getDir() {
        switch (PropertyMgr.getString("myTankDir").toUpperCase()) {
            case "UP":
                return Dir.UP;
            case "LEFT":
                return Dir.LEFT;
            case "RIGHT":
                return Dir.RIGHT;
            case "DOWN":
                return Dir.DOWN;
            default:
                return Dir.DOWN;
        }
    }
}
