package com.lulin.frame;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * awt基本没用，不必深究
 * 1.认识frame类
 *
 * @Author: LuLin
 * @Date: 2020/12/15 10:35
 */
public class T {
    public static void main(String[] args) {
        Frame frame = new Frame();//
        frame.setVisible(true);//设为可见
        frame.setSize(800, 600);//px
        frame.setResizable(false);//是否改变窗口大小——不能
        frame.setTitle("坦克一期");//标题
        //添加窗口的监听，手动可以关闭窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
