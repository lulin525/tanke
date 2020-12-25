package singleton;

/**
 * 懒加载——懒汉式+synchronized
 * 线程不安全
 *
 * @Author: LuLin
 * @Date: 2020/12/24 11:41
 */
public class LazyLoadingSynchronized04 {
    private static LazyLoadingSynchronized04 instance;

    private LazyLoadingSynchronized04() {
    }

    public static LazyLoadingSynchronized04 getInstance() {
        if (instance == null) {
            synchronized (LazyLoadingSynchronized04.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new LazyLoadingSynchronized04();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(LazyLoadingSynchronized04.getInstance().hashCode());
            }).start();
        }
    }

}
