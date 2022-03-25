package leetcode.editor.cn;
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：3 
//
// 示例 2： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2
// 
//
// 
//
// 进阶： 
//
// 
// 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。 
// 
// Related Topics 数组 哈希表 分治 计数 排序 
// 👍 1367 👎 0

//java:多数元素

public class MajorityElement2 {
    public static void main(String[] args){
        Solution solution = new MajorityElement2().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
       return recursionElement(nums,0,nums.length-1);
    }

        private Integer recursionElement(int[] nums, int low, int high) {
            //terminal
            if (low == high) {
                return nums[low];
            }
            int middle = (high-low)/2 + low;
            //求出左边的众数
            Integer left = recursionElement(nums,low,middle);
            //求出右边的众数
            Integer right = recursionElement(nums,middle+1,high);
            if (left.equals(right)) {
                return left;
            }
            int leftCount = countInRange(left,nums,low,high);
            int rightCount = countInRange(right,nums,low,high);
            return leftCount > rightCount ? left : right;
        }

        private int countInRange(Integer left, int[] nums, int low, int high) {
            int count = 0;
            for (int i = low; i <= high; i++) {
                if (nums[i] == left) {
                    count++;
                }
            }
            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

