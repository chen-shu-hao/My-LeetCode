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
 * @ClassName SelectSort
 * @Description
 * @Author shuhao.chen    @Date 2022/4/6 18:01
 */
public class SelectSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(selectSort(new int[]{3, 42, 2, 1, 312})));
    }
    //选择排序 遍历所有位置(不需要包含最后一位)的数字和后面所有的数字作比较,保留后面最小位置的索引和当前索引位置做交换
    private static int[] selectSort(int[] arr) {
        int minIndex;
        for (int i = 0; i < arr.length-1; i++) {
            minIndex = i;
            for (int j=i+1;j<arr.length-1;j++) {
                if (arr[j]<arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr,i,minIndex);
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int minIndex) {
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }
}
