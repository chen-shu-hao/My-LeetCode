package leetcode.editor.cn.sort;/**
 * <p>
 * TODO
 * </p>
 *
 * @author shuhao.chen
 * @since 2022/4/6
 */

import java.util.Arrays;

/**
 * 项目名称: My-LeetCode
 * @ClassName BubbleSort
 * @Description
 * @Author shuhao.chen    @Date 2022/4/6 17:44
 */
public class BubbleSort {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubbleSort(new int[]{2, 3, 1, 5,3123,32223,22})));
    }

    private static int[] bubbleSort(int[] arr) {
        int temp;
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {//代表遍历i次
            boolean flag = false;//加入标志 加快排序速度
            for (int j = 0; j < length - 1 - i; j++) {//代表遍历的结束位置
                if (arr[j] > arr[j+1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return arr;
    }
}
