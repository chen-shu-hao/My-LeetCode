package leetcode.editor.cn;
//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 2290 👎 0

//java:编辑距离

import java.util.Arrays;
import java.util.List;

public class P72_EditDistance {
    public static void main(String[] args) {
        Solution solution = new P72_EditDistance().new Solution();
        solution.minDistance("horse", "ros");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            //三种情况 增删改
            int n = word1.length();
            int m = word2.length();
            int[][] dp = new int[n + 1][m + 1];//dp 的base case 是i,j=-1,而数组的最小索引必须是0,所以整体向右移一位
            //初始化 注意 因为左移了一位 之后都是<= 取字符 -1
            for (int i = 0; i <= n; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= m; j++) {
                dp[0][j] = j;
            }
            //自底向上遍历
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (word1.charAt(i-1) == word2.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
            return dp[n][m];
        }

//-------------------------------2.加入缓存---------------------------------------
/*        private int[][] memo;

        public int minDistance(String word1, String word2) {
            //三种情况 增删改
            int n = word1.length();
            int m = word2.length();
            //加入缓存
            memo = new int[n][m];
            for (int[] ints : memo) {
                Arrays.fill(ints, -1);
            }
            //自顶向下递归 从最后一位开始
            return dfs(word1, n - 1, word2, m - 1);
        }

        private int dfs(String word1, int i, String word2, int j) {
            //当word1或者word2到了结尾
            if (i < 0) return j + 1;//当前索引需要+1
            if (j < 0) return i + 1;
            if (memo[i][j] != -1) {
                //说明计算过
                return memo[i][j];
            }
            //当前2个字符相等,都向前移动一位,但什么操作都没有 不要+1
            if (word1.charAt(i) == word2.charAt(j)) {
                memo[i][j] = dfs(word1, i - 1, word2, j - 1);
            } else {
                memo[i][j] = Math.min(
                        Math.min(
                                dfs(word1, i - 1, word2, j), //word1删除一位匹配
                                dfs(word1, i, word2, j - 1) //word1新增一位匹配
                        ),
                        dfs(word1, i - 1, word2, j - 1)) + 1;//word1直接替换一位匹配
            }
            return memo[i][j];
        }*/

//-----------------------1.递归调用------------------------
   /* public int minDistance(String word1, String word2) {
        //三种情况 增删改
        int n = word1.length();
        int m = word2.length();
        //自顶向下递归 从最后一位开始
        return dfs(word1, n - 1, word2, m - 1);
    }

        private int dfs(String word1, int i, String word2, int j) {
            //当word1或者word2到了结尾
            if (i < 0) return j + 1;//当前索引需要+1
            if (j < 0) return i + 1;
            //当前2个字符相等,都向前移动一位,但什么操作都没有 不要+1
            if (word1.charAt(i) == word2.charAt(j)) {
                return dfs(word1, i - 1, word2, j - 1);
            } else {
               return Math.min(
                       Math.min(
                               dfs(word1, i - 1, word2, j) , //word1删除一位匹配
                               dfs(word1, i, word2, j - 1) //word1新增一位匹配
                       ),
                       dfs(word1, i - 1, word2, j - 1)) + 1;//word1直接替换一位匹配
            }
        }*/
    }
//leetcode submit region end(Prohibit modification and deletion)

}

