package com.story.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author story
 * @CreateTIme 2020/10/8
 **/
public class GameModel {
    Tank myTank = new Tank(PropertyMgr.getInt("myTankLocX"), PropertyMgr.getInt("myTankLocY"), Dir.DOWN, Group.GOOD, this);
    java.util.List<Bullet> bullets = new ArrayList<>();
    java.util.List<Tank> enemies = new ArrayList<>();
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
    List<Explode> explodes = new ArrayList<>();


    public GameModel() {
        int initTankCount = PropertyMgr.getInt("initTankCount");
        for (int i = 0; i < initTankCount; i++) {
            enemies.add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }
    }


    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌军的数量:" + enemies.size(), 10, 80);
        g.setColor(color);

        myTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

    }

    public Tank getMainTank() {
        return myTank;
    }
}
