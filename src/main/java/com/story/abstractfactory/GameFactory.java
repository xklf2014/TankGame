package com.story.abstractfactory;

import com.story.tank.Dir;
import com.story.tank.Group;
import com.story.tank.TankFrame;

/**
 * @Author story
 * @CreateTIme 2020/10/7
 **/
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x,int y ,TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y,Dir dir,Group group, TankFrame tf);
}
