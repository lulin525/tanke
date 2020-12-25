package singleton;

/**
 * 懒加载——懒汉式+synchronized
 * 线程不安全
 *
 * @Author: LuLin
 * @Date: 2020/12/24 11:41
 */
public class LazyLoadingSynchronizedVolatileDCL05 {
    private static volatile LazyLoadingSynchronizedVolatileDCL05 instance;

    private LazyLoadingSynchronizedVolatileDCL05() {
    }

    public static LazyLoadingSynchronizedVolatileDCL05 getInstance() {
        if (instance == null) {
            synchronized (LazyLoadingSynchronizedVolatileDCL05.class) {
                if (instance == null) {
                    instance = new LazyLoadingSynchronizedVolatileDCL05();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(LazyLoadingSynchronizedVolatileDCL05.getInstance().hashCode());
            }).start();
        }
    }

}
