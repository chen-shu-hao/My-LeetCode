package leetcode.editor.cn;
//给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 
//
// 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。 
//
// 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。 
//
// 
//
// 示例 1： 
//
// 
//输入：[7,1,5,3,6,4]
//输出：5
//解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
// 
//
// 示例 2： 
//
// 
//输入：prices = [7,6,4,3,1]
//输出：0
//解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 105 
// 0 <= prices[i] <= 104 
// 
// Related Topics 数组 动态规划 
// 👍 2226 👎 0

//java:买卖股票的最佳时机
import java.util.List;
public class P121_BestTimeToBuyAndSellStock{
    public static void main(String[] args){
        Solution solution = new P121_BestTimeToBuyAndSellStock().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

       public int maxProfit(int[] prices) {
           int n = prices.length;
           if (n < 2) return 0;
        /*dp数组 dp[i][k][j] i代表是第几天,k代表能交易的最大次数 j代表 0是未持有 1是持有
        i天能获得的最大利润
         子问题
            1.i天不持有 dp[i][k][0] = Math.max(dp[i-1][k][0],dp[i-1][k][1] + prices[i])
            2.i天持有 dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0] - price[i])
         初始状态 basecase
            dp[-1][...][0] 代表还没开始也还没持有 利润 0
            dp[-1][...][1] 代表还没开始但持有了 利润 -inf 方便取最大值
            dp[...][0][0]  代表还没开始交易也没持有 0
            dp[...][0][1]  代表还没开始交易但持有了 -inf 方便取最大值
         */
           //因为k=1
         /* dp[i][k][0] = Math.max(dp[i-1][1][0],dp[i-1][1][1] + prices[i])
            dp[i][k][1] = Math.max(dp[i-1][1][1],dp[i-1][0][0] - price[i])
                        = Math.max(dp[i-1][1][1], - price[i])
           dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + prices[i])
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0][0] - price[i])
                        = Math.max(dp[i-1][1], - price[i])
                        */
           int[][] dp = new int[n][2];
           for (int i = 0; i < n; i++) {
               if (i - 1 == -1) {
                   dp[i][0] = 0;
                   dp[i][1] = -prices[i];
                   continue;
               }
               dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
               dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
           }
           return dp[n - 1][0];
       }
    /*public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]<minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                //之后的收益高的日子都只能和当前的最小值计算最高收益
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }*/
}
//leetcode submit region end(Prohibit modification and deletion)

}

