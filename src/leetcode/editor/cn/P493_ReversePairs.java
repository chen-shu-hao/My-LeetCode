package leetcode.editor.cn;
//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 
// 👍 347 👎 0

//java:翻转对
import java.util.Arrays;
import java.util.List;
public class P493_ReversePairs{
    public static void main(String[] args){
        Solution solution = new P493_ReversePairs().new Solution();
        solution.reversePairs(new int[]{1, 3, 2,4,3, 1, 1});
        for (int i = 0; i < 100; i++) {
            char c = (char) ('Q' - i);
            if ( c == 'Q') {
                System.out.println(i);
                break;
            }
        }
        System.out.println((char) ('R' + 32));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int cnt = 0;
        public int reversePairs(int[] nums) {
            mergeSort(nums, 0, nums.length - 1);
            return cnt;
        }

        void mergeSort(int[] nums, int left, int right) {
            if(left >= right) return;
            int mid = left + (right - left) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            //计算当前位置的翻转对,每次计算都是当前位置的左数组与还没有比较过的有数组比较,所以累加即可
            calculate(nums, left, mid, right);
            sort(nums, left, mid, right);
        }
        //https://leetcode-cn.com/problems/reverse-pairs/solution/cjavapython3-gui-bing-pai-xu-by-yanghk/
        void calculate(int[] nums, int left, int mid, int right) {
            int i = left; int  j = mid + 1;
            //i为左边数组的起点 j为右边数组的起点 遍历 i 判断 j中的数是否满足限制条件
            // 满足 i索引往后,之后的i再和当前的j位置比较
            // 每次都当前最新的j位置的索引值-起始值mid+1 就是当前i位置所对应的翻转对的数量
            //为什么 翻转对数量可以累加 因为每次都是左边的数组和形成的右边数组比较,不会出现已经比较过的情况
            for(; i <= mid; i++) {
                while(j <= right && nums[i] > 2L * nums[j]) {
                    j++;
                }
                cnt += j - (mid + 1);
            }
        }

        void sort(int[] nums, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left, j = mid + 1;
            int k = 0;

            while(i <= mid && j <= right) {
                temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
            }

            while(i <= mid) temp[k++] = nums[i++];
            while(j <= right) temp[k++] = nums[j++];

            for(int p = 0; p < temp.length; p++) {
                nums[left + p] = temp[p];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

