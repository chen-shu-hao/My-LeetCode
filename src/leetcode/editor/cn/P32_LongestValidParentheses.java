package leetcode.editor.cn;
//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1744 👎 0

//java:最长有效括号
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class P32_LongestValidParentheses{
    public static void main(String[] args){
        Solution solution = new P32_LongestValidParentheses().new Solution();
        solution.longestValidParentheses("(()())");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        int length = s.length();
        if (length <= 1) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        /** 放的都是索引
         * 添加一个哨兵节点 首次避免第一次就是左括号 预先放一个-1节点,栈底永远放的是最后一个不符合规则的右括号 方便计算最大值
         * 1.遇到'('就放到队列里
         * 2.遇到'）' 取出当前栈中的栈顶元素 i-stack.pop() 为当前的括号最大值
         */
        int max = 0;
        for (int i = 0; i < length; i++) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

