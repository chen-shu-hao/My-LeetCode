package leetcode.editor.cn;
//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1834 👎 0

//java:零钱兑换

import java.util.Arrays;
import java.util.List;

public class P322_CoinChange {
    public static void main(String[] args) {
        Solution solution = new P322_CoinChange().new Solution();
        System.out.println(solution.coinChange(new int[]{1, 2, 5}, 10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int coinChange(int[] coins, int amount) {
            /*子问题subproblem 当前位置的最短路径等于 当前路径和之前一位的最短路径取最小值+1
                动态数组
                dp方程
             */
            if (amount < 1) return 0;
            int[] dp = new int[amount + 1];
            //先提前将每个位置设定一个不可能的值,方便之后判断该位置有没有最短路径
            Arrays.fill(dp,amount+1);
            dp[0] = 0;
            for (int i = 1; i <= amount; i++) {
                for (int coin : coins) {
                    if (i - coin >= 0) {
                        dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                    }
                }
            }
            return dp[amount] > amount ? -1 : dp[amount];
        }
     /*   public int coinChange(int[] coins, int amount) {
            if(amount<1) return 0;
            return dfs(coins, amount, new int[amount]);
        }
        //获取最短路径
        private int dfs(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
            if(rem<0) return -1; // not valid
            if(rem==0) return 0; // completed
            if(count[rem-1] != 0) return count[rem-1]; // already computed, so reuse
            int min = Integer.MAX_VALUE;
            for(int coin : coins) {
                //往深度递归
                int res = dfs(coins, rem-coin, count);
                //每获取到一次大于0的结果并且当前最小值比已知最小值小时更新最小值
                if(res>=0 && res < min)
                    min = 1+res;
            }
            count[rem-1] = (min==Integer.MAX_VALUE) ? -1 : min;
            return count[rem-1];
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}

