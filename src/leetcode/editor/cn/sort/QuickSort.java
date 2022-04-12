package leetcode.editor.cn.sort;/**
 * <p>
 * TODO
 * </p>
 *
 * @author shuhao.chen
 * @since 2022/4/7
 */

import java.util.Arrays;

/**
 * 项目名称: My-LeetCode
 *
 * @ClassName QuickSort
 * @Description
 * @Author shuhao.chen    @Date 2022/4/7 15:35
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {5, 7, 3, 2, 4};
        quickSort(0, ints.length - 1, ints);
        System.out.println(Arrays.toString(ints));
    }

    private static void quickSort(int left, int right, int[] arr) {
        if (left >= right) return;
        int pivot = partition(left, right, arr);
        quickSort(left, pivot - 1, arr);
        quickSort(pivot + 1, right, arr);
    }

    private static int partition(int left, int right, int[] arr) {
        //取右边为pivot
        int pivot = right, index = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[pivot]) {//当前位置比pivot小 放左边
                swap(arr, i, index);
                index++;
            }
        }
        //将pivot和index交换
        swap(arr, index, pivot);
        return index;
    }

    private static void swap(int[] arr, int i, int index) {
        int temp = arr[i];
        arr[i] = arr[index];
        arr[index] = temp;
    }


  /*  private static void quickSort(int left,int right,int[] arr) {
        //定义左右指针,求出中间值
        if (left >= right) return;
        int pivot = partition(left, right,arr);
        quickSort(left,pivot-1,arr);
        quickSort(pivot+1,right,arr);
    }

    static int partition(int left, int right,int[] arr) {
    // pivot: 标杆位置，index: 起始左边位置的索引
        int pivot = right, index = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, index, i);
                index++;//index会停在最后一个大于pivot的位置,最后index和pivot交换就分成了2个数组
            }
            System.out.println(Arrays.toString(arr));
        }
        swap(arr, pivot, index);
        return index;
    }

    private static void swap(int[] arr, int counter, int i) {
        int temp = arr[counter];
        arr[counter] = arr[i];
        arr[i] = temp;
    }*/
}
