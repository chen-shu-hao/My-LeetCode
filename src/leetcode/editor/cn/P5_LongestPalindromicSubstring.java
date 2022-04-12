package leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 5038 👎 0

//java:最长回文子串
import java.util.List;
public class P5_LongestPalindromicSubstring{
    public static void main(String[] args){
        Solution solution = new P5_LongestPalindromicSubstring().new Solution();
        solution.longestPalindrome("babad");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public String longestPalindrome(String s) {
            if (null == s) {
                return "";
            }
            if (s.length() < 2) {
                return s;
            }
            //动态规划 dp方程 定义为当前i,j是否为回文串 dp[i,j] = dp[i+1,j-1] && {s[i]==s[j]} 或者 不相等 false
            // subproblem
            //dp数组初始状态 dp[i][i] = true 当字符串等于1一定为回文串
            boolean[][] dp = new boolean[s.length()][s.length()];
            for (int i = 0; i < s.length(); i++) {
                dp[i][i] = true;
            }
            //注意遍历的方向
            int begin = 0,maxLen = 1;
            for (int j = 1; j < s.length(); j++) {//遍历一个i+j形式的 先遍历左边界 再遍历右边界
                for (int i = 0; i < j; i++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = false;
                    } else {
                        if (j-i+1 <=3) {//说明是单个字符+2个相同的字符肯定是回文串
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }
                    if (dp[i][j] && j-i+1 > maxLen) {
                        maxLen = j-i+ 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin,begin+maxLen);
        }
    }


//1.双指针
 /*   public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        //双指针 以每个i作为中心位置向2边扩散
        int start = 0,end = 0;
        for (int i=0;i<s.length();i++) {
            int len1 = getSubPalindRome(s,i,i);//获得长度为奇数的最长长度
            int len2 = getSubPalindRome(s,i,i+1);//获得长度为偶数的最长长度
            int maxLen = Math.max(len1, len2);
            if (maxLen > end - start) {
                // i为回文子串的中间位置 减去 回文子串的一半长度 就等于回文子串的start；End同理，可以画图理解。
                // 至于为什么longlen-1，我理解的是单字符为轴的回文子串减不减没关系，但是已双字符为轴的回文子串不减的话算出来start的位置出错了
                start = i -(maxLen-1)/2;
                end = i + maxLen/2;
            }
        }
        return s.substring(start,end+1);
    }

        private int getSubPalindRome(String s, int left, int right) {
            int L = left,R = right;
            while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
                L--;R++;
            }
            return R - L + 1 - 2;//减去前后2个不匹配的字符
        }
    }*/
//leetcode submit region end(Prohibit modification and deletion)

}

