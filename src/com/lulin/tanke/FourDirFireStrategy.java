package com.lulin.tanke;

import com.lulin.bullet.Bullet;
import com.lulin.enums.Dir;

/**
 * 四个方向打子弹的策略
 *
 * @Author: LuLin
 * @Date: 2020/12/25 9:48
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tanke t) {
        //将子弹从坦克中心位置打出
        int bx = t.x + Tanke.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tanke.HEIGT / 2 - Bullet.HEIGT / 2;

        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bx, by, dir, t.group, t.tf);
        }

       // if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();

    }
}
