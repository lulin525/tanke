package com.lulin.tanke;

import com.lulin.bullet.Bullet;
import com.lulin.enums.Dir;
import com.lulin.frame.TankeFrame;

import java.awt.*;

/**
 * 坦克类
 *
 * @Author: LuLin
 * @Date: 2020/12/15 15:25
 */
public class Tanke {
    private int x, y;//想让动起来，坐标就不能写死
    private Dir dir = Dir.DOWN;//最开始给坦克一个方向，例如向下
    private static final int SPEND = 10;//坦克速度
    private boolean moving = false;//最初坦克是停止状态
    private TankeFrame tf = null;

    //构造方法
    public Tanke(int x, int y, Dir dir, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    //坦克画自己
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        //拿这个笔在窗口中随便画
        g.fillRect(x, y, 50, 50);//矩形——坐标是以左上角为圆心，横是x，竖是y
        g.setColor(c);
        move();//移动
    }

    //移动
    private void move() {
        if (!moving) return;//没有移动，禁止的
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

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    //发射子弹
    public void fire() {
        tf.bulletList.add(new Bullet(this.x, this.y, this.dir,this.tf));
    }
}
