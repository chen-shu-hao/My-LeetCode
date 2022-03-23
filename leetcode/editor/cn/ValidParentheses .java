//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2749 👎 0

package com.company.leetcode.editor.cn;

import org.springframework.util.StringUtils;

import java.util.Stack;

//java:有效的括号
public class P20ValidParentheses{
    public static void main(String[] args){
        Solution solution = new P20ValidParentheses().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        //对字符先进行判空判断
        if (null == s || s.length() == 0) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack stack = new Stack<>();
        for (int i=0; i<chars.length; i++){
            if ("(".equals(chars[i])) {
                stack.add("(");
            } else if ("{".equals(chars[i])) {
                stack.add("{");
            } else if ("[".equals(chars[i])) {
                stack.add("[");
            } else if (!stack.isEmpty() && chars[i]) != stack.pop()) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

