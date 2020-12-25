package singleton;

/**
 * 静态内部类方式——完美之一
 * 加载外部类，内部类不会加载
 *
 * @Author: LuLin
 * @Date: 2020/12/24 14:25
 */
public class StaticInnerClassSingleton06 {

    private StaticInnerClassSingleton06() {

    }

    //内部类初始化
    private static class Singleton06 {
        private final static StaticInnerClassSingleton06 instance = new StaticInnerClassSingleton06();
    }

    //获取实例方法
    public static StaticInnerClassSingleton06 getInstance() {
        return Singleton06.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(StaticInnerClassSingleton06.getInstance().hashCode());
            }).start();
        }
    }


}
