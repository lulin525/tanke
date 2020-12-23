package com.lulin.frame;

import com.lulin.bullet.Bullet;
import com.lulin.enums.Dir;
import com.lulin.enums.Group;
import com.lulin.tanke.Explode;
import com.lulin.tanke.Tanke;
import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克类
 * 继承Frame,重写方法
 *
 * @Author: LuLin
 * @Date: 2020/12/15 9:42
 */
public class TankeFrame extends Frame {
    Tanke tk = new Tanke(300, 500, Dir.UP, Group.GOOD, this);//坦克
    //java里面有没有内存泄漏啊？？？——当然有——和容器有关，容器用了，不清理的话，会有内存泄漏
    public List<Bullet> bulletList = new ArrayList<>();//多个子弹——数组有长度限制
    public List<Tanke> tankeList = new ArrayList<>();//敌方坦克

    Explode explode = new Explode(100, 100, this);//坦克爆炸图片

    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;//窗口的宽度和高度


    //构造方法
    public TankeFrame() {
        setVisible(true);//设为可见
        setSize(GAME_WIDTH, GAME_HEIGHT);//px
        setResizable(false);//是否改变窗口大小——不能
        setTitle("坦克一期");//标题
        //2.键盘监听
        this.addKeyListener(new MyKeyListener());
        //1.添加窗口的监听，手动可以关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //窗口闪烁——解决双缓冲问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);//整个图片画到窗口上
    }

    //窗口重新绘制时，该方法被调用
    @Override
    public void paint(Graphics g) {//画笔——系统调用
        tk.paint(g);//画笔传给主战坦克，让自己把自己给画出来
      /*  for (Bullet bt : bulletList) {//Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
            bt.Paint(g);//这种是不能删除的，处理边界问题式，会报错，内部迭代问题
        }*/
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("坦克的数量：" + tankeList.size(), 10, 60);
        g.drawString("子弹的数量：" + bulletList.size(), 10, 80);
        g.setColor(c);
        tk.paint(g);

        //画子弹的
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).Paint(g);
        }
        //画敌方坦克
        for (int i = 0; i < tankeList.size(); i++) {
            tankeList.get(i).paint(g);
        }
        //遍历子弹和坦克是否相撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankeList.size(); j++) {
                bulletList.get(i).collideWith(tankeList.get(j));//碰撞
            }
        }
        explode.Paint(g);//坦克爆炸图片


    }

    //键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;//左
        boolean bR = false;//右
        boolean bU = false;//上
        boolean bD = false;//下

        //键盘按下事件
        @Override
        public void keyPressed(KeyEvent e) {
            /**缺点：直接操作x和y，没有斜着一移动的，
             * 解决：用4个boolean值代表那个键被按下,来确定坦克的方向，
             * 之后，在方向上来处理坦克的坐标
             */
            int key = e.getKeyCode();//获取系统字符
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            //设置坦克主站的方向
            setMainTankeDir();
        }

        //键盘松开事件
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();//获取系统字符
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tk.fire();//发射子弹
                    break;
                default:
                    break;
            }
            //设置坦克主站的方向
            setMainTankeDir();
        }

        //设置主战坦克的方向
        private void setMainTankeDir() {
            if (!bL && !bR && !bU && !bD) tk.setMoving(false);//都没有按下时，不动
            else {
                tk.setMoving(true);//可以移动
                if (bL) tk.setDir(Dir.LEFT);
                if (bR) tk.setDir(Dir.RIGHT);
                if (bU) tk.setDir(Dir.UP);
                if (bD) tk.setDir(Dir.DOWN);
            }
        }
    }
}
