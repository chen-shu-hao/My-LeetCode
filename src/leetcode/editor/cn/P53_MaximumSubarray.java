package leetcode.editor.cn;
//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// -104 <= nums[i] <= 104 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治 动态规划 
// 👍 4630 👎 0

//java:最大子数组和

public class P53_MaximumSubarray{
    public static void main(String[] args){
        Solution solution = new P53_MaximumSubarray().new Solution();
        //System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(solution.maxSubArray(new int[]{-1,-2}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int maxSubArray(int[] nums) {
          //subproblem
            // 1前一个位置的最大和+当前索引或者不加当前索引的最大值dp[i]=Math.max(dp[i-1],0)+nums[i]
          //dp动态数组 dp[i]
          //dp动态方程 dp[i]=Math.max(dp[i-1],0)+nums[i]
            if(nums.length == 1) {
                return nums[0];
            }
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            int result = dp[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], 0) + nums[i];
                result = Math.max(result, dp[i]);
            }
            return result;
        }
  /*  public int maxSubArray(int[] nums) {
        //暴力递归
    //    枚举所有的起点和接下来的索引的和,取最大值
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            //起点必然不是负数
            if (nums[i] < 0) {
                continue;
            }
            int sum = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                maxSum = Math.max(sum,maxSum);
            }
        }
        return maxSum;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}

