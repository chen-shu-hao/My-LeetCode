package leetcode.editor.cn.sort;/**
 * <p>
 * TODO
 * </p>
 *
 * @author shuhao.chen
 * @since 2022/4/8
 */

import java.util.Arrays;

/**
 * 项目名称: My-LeetCode
 *
 * @ClassName MergeSort
 * @Description
 * @Author shuhao.chen    @Date 2022/4/8 9:59
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] ints = {5, 7, 3, 2, 4};
        mergeSort(ints, 0, ints.length-1);
        System.out.println(Arrays.toString(ints));
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}
