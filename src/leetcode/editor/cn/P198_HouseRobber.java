package leetcode.editor.cn;
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 数组 动态规划 
// 👍 2010 👎 0

//java:打家劫舍
import java.util.List;
public class P198_HouseRobber{
    public static void main(String[] args){
        Solution solution = new P198_HouseRobber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            //subproblem dp为当前的索引能获取的最大值(不管偷还是不偷)
            //第i间房子不偷 最大就为 dp[i-1]  第i间房子偷 dp[i-2]+nums[i]
            //dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
            // dp[i]=Math.max(dp[i],dp[i-1])
            //初始化 dp[0] = nums[0]; dp[1] = Math.max(nums[0],nums[1])
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
            }
            return dp[nums.length-1];
        }







    public int rob1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //0代表不偷  1代表偷
        //subproblem  因为可以跳着偷
        // 不偷第i个房子dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1])
        // 偷第i个房子dp 前面一个肯定不能偷dp[i][1] = dp[i-1][0] + nums[i];
        //dp方程 dp[i][0]
        //dp方程状态初始化
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[nums.length-1][0],dp[nums.length-1][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

