package singleton;

/**
 * 懒加载——懒汉式
 * 线程不安全
 *
 * @Author: LuLin
 * @Date: 2020/12/24 11:41
 */
public class LazyLoadingSingleton02 {
    private static LazyLoadingSingleton02 instance;

    private LazyLoadingSingleton02() {
    }

    public static LazyLoadingSingleton02 getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new LazyLoadingSingleton02();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazyLoadingSingleton02.getInstance().hashCode());
            }).start();
        }
    }

}
