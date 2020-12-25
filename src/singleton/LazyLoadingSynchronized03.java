package singleton;

/**
 * 懒加载——懒汉式+synchronized
 * 线程不安全
 *
 * @Author: LuLin
 * @Date: 2020/12/24 11:41
 */
public class LazyLoadingSynchronized03 {
    private static LazyLoadingSynchronized03 instance;

    private LazyLoadingSynchronized03() {
    }

    public static synchronized LazyLoadingSynchronized03 getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazyLoadingSynchronized03();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(LazyLoadingSynchronized03.getInstance().hashCode());
            }).start();
        }
    }

}
