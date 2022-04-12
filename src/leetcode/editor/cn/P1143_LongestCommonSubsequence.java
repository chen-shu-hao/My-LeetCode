package leetcode.editor.cn;
//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 字符串 动态规划 
// 👍 908 👎 0

//java:最长公共子序列

import java.util.List;

public class P1143_LongestCommonSubsequence {
    public static void main(String[] args) {
        Solution solution = new P1143_LongestCommonSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            //子问题
        /*
         /比较2个字符串一般都是要转换成二维数组
        /*先找到dp动态方程
        1.如果text1最后一个字符和text2最后一个字符相等,
        那他们的最长公共子序列的问题就转换为dp[i][j]=dp[i-1][j-1]+1;
        比如 ace bc 就转换为 ace b 或者 ac bc 的最长公共子序列
        2.如果不相等 dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        递归的初始状态是抽象出来的dp[0][0] = 0;

            //初始化dp数组
        2种情况
         1.当最后一个字符相等时 dp[i][j]=dp[i-1][j-1]+1;
         2.当最后2个字符不相等时 dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
        //动态数组 dp[i][j]
        //dp方程
        1.当最后一个字符相等时 dp[i][j]=dp[i-1][j-1]+1;
         2.当最后2个字符不相等时 dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
         */
            int m = text1.length();
            int n = text2.length();
            int[][] dp = new int[m + 1][n + 1];
            dp[0][0] = 0;
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                    }
                }
            }
            return dp[m][n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

