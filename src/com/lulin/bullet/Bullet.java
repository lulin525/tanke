package com.lulin.bullet;

import com.lulin.enums.Dir;

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
    private static int WIDTH = 30, HEIGT = 30;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //子弹画自己
    public void Paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x, y, WIDTH, HEIGT);//圆
        g.setColor(c);
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
}
