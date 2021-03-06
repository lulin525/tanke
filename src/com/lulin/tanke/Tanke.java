package com.lulin.tanke;

import com.lulin.config.PropertyMgr;
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
    int x, y;//想让动起来，坐标就不能写死
    Dir dir = Dir.DOWN;//最开始给坦克一个方向，例如向下
    private static final int SPEND = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));//坦克速度

    public static int WIDTH = ResourceMgr.goodTankD.getWidth();//坦克图片的宽
    public static int HEIGT = ResourceMgr.goodTankD.getHeight();//坦克图片的高

    private boolean moving = true;//最初坦克是停止状态
    private boolean living = true;//活的
    TankeFrame tf = null;

    private Random random = new Random();//随机产生
    //用于分组，区分子弹
    Group group = Group.BAD;//坏蛋

    public Rectangle rectBullet = new Rectangle();

    FireStrategy fire;

    //构造方法
    public Tanke(int x, int y, Dir dir, Group group, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        rectBullet.x = this.x;
        rectBullet.y = this.y;
        rectBullet.width = WIDTH;
        rectBullet.height = HEIGT;
        if (group == Group.GOOD)
            fire = new FourDirFireStrategy();
        else
            fire = new DefaultFireStrategy();
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
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
        //移动完后，更新矩形坐标
        rectBullet.x = this.x;
        rectBullet.y = this.y;

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire(); //发射子弹
        if (this.group == Group.BAD && random.nextInt(10) > 8)
            randomDir();//定义随机方向——敌方坦克

        //边界检测
        boundsCheck();

        //移动完后，更新矩形坐标
        rectBullet.x = this.x;
        rectBullet.y = this.y;
    }

    //边界检测
    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankeFrame.GAME_WIDTH - Tanke.WIDTH) x = TankeFrame.GAME_WIDTH - Tanke.WIDTH;
        if (this.y > TankeFrame.GAME_HEIGHT - Tanke.HEIGT) y = TankeFrame.GAME_HEIGHT - Tanke.HEIGT;
    }

    //定义随机方向
    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
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
    public void fire() {//一般能穿参数就传参数，但是每次都需要new对，需要传参数就弄单例
        fire.fire(this);
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
