package com.story.tank;

/**
 * @Author story
 * @CreateTIme 2020/10/3
 **/
public class Client {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {
            tf.enemies.add(new Tank(50 + i * 80,200,Dir.DOWN,tf));
        }
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
