package com.story.abstractfactory;

import com.story.tank.*;

/**
 * @Author story
 * @CreateTIme 2020/10/7
 **/
public class FactoryNew extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Tank(x,y,dir,group,tf);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new ExplodeNew(x, y, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new BulletNew(x, y, dir, group, tf);
    }
}
