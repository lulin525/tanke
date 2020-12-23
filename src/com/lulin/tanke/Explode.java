package com.lulin.tanke;

import com.lulin.audio.Audio;
import com.lulin.frame.TankeFrame;
import com.lulin.staticflie.ResourceMgr;

import java.awt.*;

/**
 * 坦克爆炸
 *
 * @Author: LuLin
 * @Date: 2020/12/21 21:15
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//子弹图片的宽
    public static int HEIGT = ResourceMgr.explodes[0].getHeight();//子弹图片的高

    private int x, y;
    private TankeFrame tf;

    private int step = 0;

    public Explode(int x, int y, TankeFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(()->new Audio("audio/explode.wav").loop()).start();//爆炸声
    }

    //画爆炸图片
    public void Paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length)
            tf.explodeList.remove(this);
    }

}
