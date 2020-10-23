package com.story.tank;


import com.story.tank.net.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.*;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class TankFrame extends Frame {
    Random r = new Random();
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), getDir(), Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<>();
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");
    List<Explode> explodes = new ArrayList<>();
    public Map<UUID, Tank> tanks = new HashMap<>();

    public static final TankFrame INSTANCE = new TankFrame();


    public void addTank(Tank tank) {
        tanks.put(tank.getId(), tank);
        //System.out.println("tank size"+tanks.size());
    }

    public Tank findByUUID(UUID id) {
        return tanks.get(id);
    }

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("tank game");
        setResizable(true);

        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Client.INSTANCE.send(new TankExitMsg(getMainTank().getId()));
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        print(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        //g.drawString("敌军的数量:" + enemies.size(), 10, 80);
        g.setColor(color);

        myTank.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        tanks.values().stream().forEach((e) -> e.paint(g));

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        Collection<Tank> values = tanks.values();
        for (int i = 0; i < bullets.size(); i++) {
            for (Tank t : values){
                bullets.get(i).collideWith(t);
            }
        }

    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public Bullet findBulletByUUID(UUID bulletId){
        for (int i = 0; i < bullets.size(); i++) {
            if (bulletId.equals(bullets.get(i).getId())){
                return bullets.get(i);
            }
        }
        return null;
    }


    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                //case KeyEvent.VK_CONTROL:
                    //myTank.fire();
                    //break;
                default:
                    break;
            }
            setMainTankDir();


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            Dir dir = myTank.getDir();

            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
                Client.INSTANCE.send(new TankStopMsg(getMainTank()));
            }else {
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);

                if ( dir != myTank.getDir()){
                    Client.INSTANCE.send(new TankDirChangedMsg(getMainTank()));
                }

                if (!myTank.isMoving())
                    Client.INSTANCE.send(new TankMovingMsg(getMainTank()));
                myTank.setMoving(true);
            }


        }

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

    public Tank getMainTank() {
        return myTank;
    }
}
