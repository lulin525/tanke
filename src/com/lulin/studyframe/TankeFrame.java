package com.lulin.studyframe;

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
    //想让动起来，坐标就不能写死
    int x = 200, y = 200;

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
        System.out.println("------");
        //拿这个笔在窗口中随便画
        g.fillRect(x, y, 50, 50);//矩形——坐标是以左上角为圆心，横是x，竖是y
//     x += 10;
//        y += 10;


    }

    //键盘监听处理类
    class MyKeyListener extends KeyAdapter {
        //键盘按下事件
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("----按下");
            x += 20;
            y += 20;
            //刷新窗口
            //repaint();//会默认调用paint方法

        }

        //键盘松开事件
        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("----松开");

        }
    }
}
