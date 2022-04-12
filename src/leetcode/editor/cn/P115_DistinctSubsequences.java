package leetcode.editor.cn;
//给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。 
//
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "rabbbit", t = "rabbit"
//输出：3
//解释：
//如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
//rabbbit
//rabbbit
//rabbbit 
//
// 示例 2： 
//
// 
//输入：s = "babgbag", t = "bag"
//输出：5
//解释：
//如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。 
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length, t.length <= 1000 
// s 和 t 由英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 731 👎 0

//java:不同的子序列

import java.util.Arrays;
import java.util.List;

public class P115_DistinctSubsequences {
    public static void main(String[] args) {
        Solution solution = new P115_DistinctSubsequences().new Solution();
        System.out.println(solution.numDistinct("rabbbit", "rabbit"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDistinct(String s, String t) {
            int m = s.length();
            int n = t.length();
            if (m < n) return 0;
            int[][] dp = new int[m + 1][n + 1];
            //dp的意义就是s[i..]t[j..] s的前i个字符串中t的前j个字符串的子序列个数
            //当j=0时 t是空字符串,空字符串是任何字符串的子序列 当 i=0是 s是空字符串 不可能有任何子序列=0
            for (int i = 0; i <= m;i++) dp[i][0] = 1;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i-1) == t.charAt(j-1)) {
                        //如果当前字符串相等
                        // 1.可以要当前s字符串,也可以不要当前字符串
                        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                    } else {
                        //当前字符串一定不选
                        dp[i][j] = dp[i - 1][j];
                    }

                }
            }
            return dp[m][n];
        }

  /*      int[][] memo;
        public int numDistinct(String s, String t) {
            // 站在 t 视角 加备忘录
            memo = new int[s.length()][t.length()];
            for (int[] ints : memo) {
                Arrays.fill(ints, -1);
            }
            return dfs(s, 0, t, 0);
        }

        private int dfs(String s, int i, String t, int j) {
            //terminal
            if (j == t.length()) return 1;
            if (i == s.length()) return 0;
            int res = 0;
            //遍历s寻找和当前t[0..]中的字符串继续下探，已经遍历过的不需要在遍历
            for (int k=i;k<s.length();k++) {
                if (s.charAt(k) == t.charAt(j)) {
                    if (k+1 < s.length() && j+1 < t.length() && memo[k+1][j+1] != -1) {
                        return memo[k+1][j+1];
                    }
                    res += dfs(s, k + 1, t, j + 1);
                }
            }
            memo[i][j] = res;
            return memo[i][j] ;
        }*/

//-----------------------------1.递归--------------
      /*  public int numDistinct(String s, String t) {
            // 站在 t 视角的暴力解，超时，就算加备忘录效率也比较低
            return dfs(s, 0, t, 0);
        }

        private int dfs(String s, int i, String t, int j) {
            //terminal
            if (j == t.length()) return 1;
            if (i == s.length()) return 0;
            int res = 0;
            //遍历s寻找和当前t[0..]中的字符串继续下探，已经遍历过的不需要在遍历
            for (int k=i;k<s.length();k++) {
                if (s.charAt(k) == t.charAt(j)) {
                    res += dfs(s, k + 1, t, j + 1);
                }
            }
            return res;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}

