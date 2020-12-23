package com.lulin.bullet;

import com.lulin.enums.Dir;
import com.lulin.enums.Group;
import com.lulin.frame.TankeFrame;
import com.lulin.staticflie.ResourceMgr;
import com.lulin.tanke.Explode;
import com.lulin.tanke.Tanke;

import java.awt.*;

/**
 * 子弹类
 *
 * @Author: LuLin
 * @Date: 2020/12/15 16:16
 */
public class Bullet {
    private static final int SPEND = 10;//子弹速度
    private int x, y;//坐标
    private Dir dir;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();//子弹图片的宽
    public static int HEIGT = ResourceMgr.bulletD.getHeight();//子弹图片的高

    private boolean living = true;//处理边界问题
    private TankeFrame tf;

    //用于分组，区分子弹
    private Group group = Group.BAD;//坏蛋

    public Bullet(int x, int y, Dir dir, Group group, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    //子弹画自己
    public void Paint(Graphics g) {
        if (!living) {//如果不活了
            tf.bulletList.remove(this);//最简单的，有bug
        }
     /*   Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HEIGT);//圆
        g.setColor(c);*/
        //根据方向画子弹图片
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();//移动
    }

    //移动
    private void move() {
        switch (dir) {//根据方向进行位移
            case LEFT:
                x -= SPEND;
                break;
            case RIGHT:
                x += SPEND;
                break;
            case UP:
                y -= SPEND;
                break;
            case DOWN:
                y += SPEND;
                break;
        }
        if (x < 0 || y < 0 || x > TankeFrame.GAME_WIDTH || y > TankeFrame.GAME_HEIGHT)
            living = false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //子弹和坦克碰撞
    public void collideWith(Tanke tanke) {
        if (this.group == tanke.getGroup()) return;

        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGT);//子弹的矩形
        Rectangle rectTanke = new Rectangle(tanke.getX(), tanke.getY(), tanke.WIDTH, tanke.HEIGT);//坦克的矩形
        if (rectBullet.intersects(rectTanke)) {//判断两个方块是否相交
            //碰到就死了
            tanke.die();
            this.die();

            //将子弹从坦克中心位置打出
            int ex = tanke.getX() + Tanke.WIDTH / 2 - Explode.WIDTH / 2;
            int ey = tanke.getY() + Tanke.HEIGT / 2 - Explode.HEIGT / 2;

            tf.explodeList.add(new Explode(ex, ey, tf));
        }
    }

    //死
    public void die() {
        this.living = false;
    }
}
