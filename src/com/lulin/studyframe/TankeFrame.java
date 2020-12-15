package com.lulin.studyframe;

import com.lulin.enums.Dir;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 继承Frame,重写方法
 *
 * @Author: LuLin
 * @Date: 2020/12/15 9:42
 */
public class TankeFrame extends Frame {
    int x = 200, y = 200; //想让动起来，坐标就不能写死
    Dir dir = Dir.DOWN;//最开始给坦克一个方向，例如向下
    private static final int SPEND = 10;//坦克速度

    //构造方法
    public TankeFrame() {
        setVisible(true);//设为可见
        setSize(800, 600);//px
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

    //窗口重新绘制时，该方法被调用
    @Override
    public void paint(Graphics g) {//画笔——系统调用
        System.out.println("---走起---");
        //拿这个笔在窗口中随便画
        g.fillRect(x, y, 50, 50);//矩形——坐标是以左上角为圆心，横是x，竖是y
        switch (dir) {
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
                    //x -= 20;
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

            //刷新窗口
            //repaint();//会默认调用paint方法

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
                default:
                    break;
            }
            //设置坦克主站的方向
            setMainTankeDir();
        }

        //设置坦克主站的方向
        private void setMainTankeDir() {
            if (bL) dir = Dir.LEFT;
            if (bR) dir = Dir.RIGHT;
            if (bU) dir = Dir.UP;
            if (bD) dir = Dir.DOWN;
        }
    }
}
