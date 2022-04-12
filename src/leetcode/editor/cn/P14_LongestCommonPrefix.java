package leetcode.editor.cn;
//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 2174 👎 0

//java:最长公共前缀
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P14_LongestCommonPrefix{
    public static void main(String[] args){
        Solution solution = new P14_LongestCommonPrefix().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int length = strs[0].length();
        for (int i = 0;i<length;i++) {
            char c = strs[0].charAt(i);
            for (int j=1;j<strs.length;j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

