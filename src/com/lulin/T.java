package com.lulin;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * awt基本没用，不必深究
 *
 * @Author: LuLin
 * @Date: 2020/12/14 21:20
 */
public class T {
    public static void main(String[] args) {
        Frame frame = new Frame();//
        frame.setVisible(true);//设为可见
        frame.setSize(800, 600);//px
        frame.setResizable(false);//是否改变窗口大小——不能
        frame.setTitle("坦克一期");//标题

        frame.addWindowListener(new WindowAdapter() {//添加窗口的监听，手动可以关闭窗口
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
