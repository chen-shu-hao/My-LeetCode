package leetcode.editor.cn;
//给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。 
//
// 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入:s = "abccccdd"
//输出:7
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
//
// 示例 2: 
//
// 
//输入:s = "a"
//输入:1
// 
//
// 示例 3: 
//
// 
//输入:s = "bb"
//输入: 2
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 2000 
// s 只能由小写和/或大写英文字母组成 
// 
// Related Topics 贪心 哈希表 字符串 
// 👍 405 👎 0

//java:最长回文串
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P409_LongestPalindrome{
    public static void main(String[] args){
        Solution solution = new P409_LongestPalindrome().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindrome(String s) {
        int[] counts = new int[128];
        for (int i=0;i<s.length();i++) {
            counts[s.charAt(i)]++;
        }
        //遍历counts
        int res = 0;
        for (int count : counts) {
            res += count/2 * 2; // 必定是偶数
            //当我们发现第一个奇数时,res+1(变成了奇数),之后都是偶数就不会加了;
            if (count % 2 == 1 && res % 2 == 0) {
                res++;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

