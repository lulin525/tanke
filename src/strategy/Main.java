package strategy;

import java.util.Arrays;

/**
 * @Author: LuLin
 * @Date: 2020/12/24 10:53
 */
public class Main {
    public static void main(String[] args) {
        int[] arr = {9, -2, 3, 2, 7, 5, 4, 8};
        Sorter sorter = new Sorter();
        sorter.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
