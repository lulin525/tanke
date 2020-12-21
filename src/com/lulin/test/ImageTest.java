package com.lulin.test;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 图片测试类
 *
 * @Author: LuLin
 * @Date: 2020/12/21 10:57
 */
public class ImageTest {
    @Test
    public void test() {
        try {
            //1.从硬盘上读取取图片，有很大的局限性——地址写死了
            BufferedImage image1 = ImageIO.read(new File("E:/ideaWorkSpaces/tanke/src/images/0.gif"));
           //2.一般都这种方式——图片images/bulletD.gif放在src目录下
            BufferedImage  image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
          // assertNotNull(image2);//断言
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
