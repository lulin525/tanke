package strategy;

/**
 * @Author: LuLin
 * @Date: 2020/12/24 10:55
 */
public class Sorter {

    //选择排序
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            awap(arr, i, minPos);//交换
        }
    }

    //交换
    private static void awap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
