package com.story.tank;

import com.story.tank.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class GameModel {
    Tank myTank;
    //static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
    private List<GameObject> gameObjects = new ArrayList<>();
    ColliderChain colliderChain = new ColliderChain();
    private static final GameModel INSTANCE = new GameModel();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    static {
        INSTANCE.init();
    }

    private GameModel() {

    }

    public void init(){
        myTank = new Tank(PropertyMgr.getInt("myTankLocX"), PropertyMgr.getInt("myTankLocY"), getDir(), Group.GOOD);

        int initTankCount = PropertyMgr.getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }

        for (int i = 0; i < 20; i++) {
            new Wall(100 + i * Wall.WIDTH, 300);
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
                colliderChain.collide(o1, o2);

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

    public void save(){
        File file = new File("src/main/resources/data/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(myTank);
            oos.writeObject(gameObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void load() {
        File file = new File("src/main/resources/data/tank.data");
        ObjectInputStream ois= null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank) ois.readObject();
            gameObjects = (List)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
