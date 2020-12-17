package com.lulin.bullet;

import com.lulin.enums.Dir;
import com.lulin.frame.TankeFrame;

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

    private boolean live = true;//处理边界问题
    private TankeFrame tf;

    public Bullet(int x, int y, Dir dir, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    //子弹画自己
    public void Paint(Graphics g) {
        if (!live) {//如果不活了
            tf.bulletList.remove(this);//最简单的，有bug
        }
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
        if (x < 0 || y < 0 || x > TankeFrame.GAME_WIDTH || y > TankeFrame.GAME_HEIGHT)
            live = false;
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
