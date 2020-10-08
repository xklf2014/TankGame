package com.story.tank;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
