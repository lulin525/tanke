package com.lulin.staticflie;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author: LuLin
 * @Date: 2020/12/21 11:38
 */
public class ResourceMgr {
    //定义左右上下
    public static BufferedImage tankL,tankR,tankU, tankD;///坦克
    public static BufferedImage bulletL,bulletR,bulletU, bulletD;///坦克


    //静态语句块初始化
    static {
        try {
            //加载坦克左右上下图片
            tankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));

            //加载子弹图片
            bulletL=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletD=  ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            bulletU=  ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
