package com.story.tank;

/**
 * @Author story
 * @CreateTIme 2020/10/6
 **/
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2 - 1;
        int bY = tank.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2 + 4;

        new Bullet(bX, bY, tank.getDir(), tank.getGroup(), tank.getTf());

        if (tank.getGroup() == Group.GOOD) new Thread(() -> {
            new Audio("audio/tank_fire.wav").play();
        }).start();
    }
}
