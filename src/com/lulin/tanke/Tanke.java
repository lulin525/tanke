package com.lulin.tanke;

import com.lulin.bullet.Bullet;
import com.lulin.enums.Dir;
import com.lulin.enums.Group;
import com.lulin.frame.TankeFrame;
import com.lulin.staticflie.ResourceMgr;

import java.awt.*;
import java.util.Random;

/**
 * 坦克类
 *
 * @Author: LuLin
 * @Date: 2020/12/15 15:25
 */
public class Tanke {
    private int x, y;//想让动起来，坐标就不能写死
    private Dir dir =Dir.DOWN;//最开始给坦克一个方向，例如向下
    private static final int SPEND = 2;//坦克速度

    public static int WIDTH = ResourceMgr.goodTankD.getWidth();//坦克图片的宽
    public static int HEIGT = ResourceMgr.goodTankD.getHeight();//坦克图片的高

    private boolean moving = true;//最初坦克是停止状态
    private boolean living = true;//活的
    private TankeFrame tf = null;

    private Random random = new Random();//随机产生
    //用于分组，区分子弹
    private Group group = Group.BAD;//坏蛋

    //构造方法
    public Tanke(int x, int y, Dir dir, Group group, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    //坦克画自己
    public void paint(Graphics g) {
       /*  Color c = g.getColor();
        g.setColor(Color.YELLOW);
        //拿这个笔在窗口中随便画
        g.fillRect(x, y, 50, 50);//矩形——坐标是以左上角为圆心，横是x，竖是y
        g.setColor(c);改成画图片*/
        //根据方向画图片
        if (!living) tf.tankeList.remove(this);//移除

        //如果不活
        switch (dir) {
            case LEFT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
                break;
        }

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
      if (this.group==Group.BAD && random.nextInt(100) > 95)
          this.fire(); //发射子弹
      if (this.group==Group.BAD&& random.nextInt(10) > 8)
          randomDir();//定义随机方向——敌方坦克
    }
    //定义随机方向
    private void randomDir() {

        this.dir=Dir.values()[random.nextInt(4)];
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //发射子弹
    public void fire() {
        //将子弹从坦克中心位置打出
        int bx = this.x + Tanke.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tanke.HEIGT / 2 - Bullet.HEIGT / 2;
        tf.bulletList.add(new Bullet(bx, by, this.dir, this.group, this.tf));
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //坦克死
    public void die() {
        this.living = false;
    }
}
