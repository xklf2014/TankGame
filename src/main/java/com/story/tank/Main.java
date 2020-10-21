package com.story.tank;

import com.story.tank.net.Client;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);
        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

        Client.INSTANCE.connect();
    }
}
