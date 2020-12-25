package com.lulin.tanke;

import com.lulin.audio.Audio;
import com.lulin.bullet.Bullet;
import com.lulin.enums.Group;

/**
 * 默认的开关策略
 *
 * @Author: LuLin
 * @Date: 2020/12/25 9:34
 */
public class DefaultFireStrategy  implements FireStrategy{

    @Override
    public void fire(Tanke t) {
        //将子弹从坦克中心位置打出
        int bx = t.x + Tanke.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = t.y + Tanke.HEIGT / 2 - Bullet.HEIGT / 2;
       new Bullet(bx, by, t.dir, t.group, t.tf);

       if (t.group== Group.GOOD ) new Thread(()->new Audio("audio/tank_fire.wav").loop()).start();
    }
}
