package leetcode.editor.cn;
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
// 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
// Related Topics 递归 字符串 动态规划 
// 👍 2901 👎 0

//java:正则表达式匹配

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P10_RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new P10_RegularExpressionMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private Map<String,Boolean> memo;//有没有用过 用map当缓存
        int slen;
        int plen;

        public boolean isMatch(String s, String p) {
            slen = s.length();
            plen = p.length();
            memo = new HashMap<>();
            return dfs(s, 0, p, 0);
        }

        private boolean dfs(String s, int i, String p, int j) {
            //如果字符规律串已经到顶了,说明已经匹配完成了,判断s是否也已经到了尾部
            if (j == plen) {
                return i == slen;
            }
            //如果s已经到了末尾,那么此时只有当p后面都是类似a*这种才行（能形成空字符串）
            if (i == slen) {
                if ((plen - j) % 2 == 1) {//此处是长度-1，不是索引-1
                    return false;
                }
                for (; j + 1 < plen; j += 2) {
                    if (p.charAt(j + 1) != '*') {
                        return false;
                    }
                }
                return true;
            }
            String key = i + "," + j;
            if (memo.containsKey(key)) return memo.get(key);
            boolean res;
            //1.判断当前字符串是不是相等
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                //判断接下来的一个字符包不包含*
                if (j < plen-1 && p.charAt(j+1) == '*') {
                    //包含*,j可以用0次或多次
                    //0次说明i也没被消耗
                    res = dfs(s,i,p,j+2) || dfs(s,i+1,p,j);
                } else {
                    //不包含*,那2个必须匹配
                    res = dfs(s, i + 1, p, j + 1);
                }
            } else {
                if (j < plen-1 && p.charAt(j+1) == '*') {
                    //包含*,要和之前的形成空串,p用2个字符和后面的匹配
                    res = dfs(s, i, p, j + 2);
                } else {
                    //不包含*,当前字符串不匹配,不能形成空串 肯定false
                    return false;
                }
            }
            memo.put(key,res);
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

