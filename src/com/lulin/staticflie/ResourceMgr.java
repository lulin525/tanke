package com.lulin.staticflie;

import com.lulin.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: LuLin
 * @Date: 2020/12/21 11:38
 */
public class ResourceMgr {
    //定义左右上下
    public static BufferedImage goodTankL, goodTankR,goodTankU, goodTankD;///坦克
    public static BufferedImage badTankL, badTankR,badTankU, badTankD;///坦克
    public static BufferedImage bulletL,bulletR, bulletU, bulletD;///坦克
    public static BufferedImage[] explodes = new BufferedImage[16];///坦克爆炸


    //静态语句块初始化
    static {
        try {
            //加载坦克左右上下图片
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);

            //加载敌军坦克左右上下图片
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);

            //加载子弹图片
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            bulletR= ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);

            //加载坦克爆炸图片
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
