package com.story.tank;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = PropertyMgr.getInt("initTankCount");
        System.out.println(initTankCount);
        for (int i = 0; i < initTankCount; i++) {
            //tf.enemies.add(tf.gf.createTank(50 + i * 80,200,Dir.DOWN,Group.BAD,tf));
            tf.enemies.add(new Tank(50 + i * 80,200,Dir.DOWN,Group.BAD,tf));
        }
        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
