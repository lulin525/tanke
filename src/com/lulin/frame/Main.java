package com.lulin.frame;

import com.lulin.audio.Audio;
import com.lulin.config.PropertyMgr;
import com.lulin.enums.Dir;
import com.lulin.enums.Group;
import com.lulin.tanke.Tanke;

/**
 * awt基本没用，不必深究
 *
 * @Author: LuLin
 * @Date: 2020/12/14 21:20
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankeFrame tf = new TankeFrame();

        int initTankeCount = Integer.parseInt((String) PropertyMgr.get("initTankeCount"));//敌方坦克数

        //初始化敌方坦克
        for (int i = 0; i < initTankeCount; i++) {
            tf.tankeList.add(new Tanke(50 + i * 80, 200, Dir.DOWN, Group.BAD, tf));
        }
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        //最简单的自动刷新窗口
        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
