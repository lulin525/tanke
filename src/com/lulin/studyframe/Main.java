package com.lulin.studyframe;

/**
 * awt基本没用，不必深究
 *
 * @Author: LuLin
 * @Date: 2020/12/14 21:20
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankeFrame tf = new TankeFrame();
        //最简单的自动刷新窗口
        while (true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
