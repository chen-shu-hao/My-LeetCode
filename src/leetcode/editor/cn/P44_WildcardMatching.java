package leetcode.editor.cn;
//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。 
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
// 
//
// 两个字符串完全匹配才算匹配成功。 
//
// 说明: 
//
// 
// s 可能为空，且只包含从 a-z 的小写字母。 
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。 
// 
//
// 示例 1: 
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。 
//
// 示例 2: 
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
// 
//
// 示例 3: 
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
// 
//
// 示例 4: 
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
// 
//
// 示例 5: 
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false 
// Related Topics 贪心 递归 字符串 动态规划 
// 👍 857 👎 0

//java:通配符匹配

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P44_WildcardMatching {
    public static void main(String[] args) {
        Solution solution = new P44_WildcardMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private Map<String, Boolean> memo;
        int slen;
        int plen;
        public boolean isMatch(String s, String p) {
            if (s == null || p == null) return false;
            slen = s.length();plen = p.length();
            if (slen == 0 && slen == plen) return true;
            memo = new HashMap<>();
            return dp(s,0,p,0);//当前s[i..]和p[i..]能否完全匹配
        }

        private boolean dp(String s, int i, String p, int j) {
            //terminal
            if (j == plen) {
                return i == slen;
            }
            if (i == slen) {
                //j和j后面必须全部是*。
                for (;j<plen;j++) {
                    if (p.charAt(j) != '*') return false;
                }
                return true;
            }
            String key = i + "," + j;
            if (memo.containsKey(key)) return memo.get(key);
            boolean res = false;
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                res = dp(s, i + 1, p, j + 1);
            } else if (p.charAt(j) == '*'){
                //使用当前* 0次或多次 或者不用
                res = dp(s, i , p, j + 1) || dp(s, i + 1, p, j );;
            } else {
                //未匹配上
                return false;
            }
            memo.put(key, res);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

