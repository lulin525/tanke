package com.lulin.tanke;

import com.lulin.enums.Dir;

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

    //构造方法
    public Tanke(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    //坦克画自己
    public void paint(Graphics g) {
        //拿这个笔在窗口中随便画
        g.fillRect(x, y, 50, 50);//矩形——坐标是以左上角为圆心，横是x，竖是y
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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Dir getDir() {
        return dir;
    }
}
