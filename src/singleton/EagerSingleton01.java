package singleton;

import javax.sound.sampled.SourceDataLine;

/**
 * 饿汉式单例模式
 *
 * @Author: LuLin
 * @Date: 2020/12/24 10:30
 */
public class EagerSingleton01 {
    private static final EagerSingleton01 singleton01 = new EagerSingleton01();

    private EagerSingleton01() { }

    public static EagerSingleton01 getInstance() {
        return singleton01;
    }

    public static void main(String[] args) {
        EagerSingleton01 s1 =EagerSingleton01.getInstance();
        EagerSingleton01 s2 = EagerSingleton01.getInstance();
        System.out.println(s1 == s2);
    }

}
