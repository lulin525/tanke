package singleton;

/**
 * 枚举单例
 *
 * @Author: LuLin
 * @Date: 2020/12/24 10:23
 */
public enum EnumSingleton07 {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(EnumSingleton07.INSTANCE.hashCode());
                }
            ).start();
        }
    }

}
