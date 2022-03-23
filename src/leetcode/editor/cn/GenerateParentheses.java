package leetcode.editor.cn;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 2473 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//java:括号生成
public class GenerateParentheses{
    public static void main(String[] args){
        Solution solution = new GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        generate(0, 2 * n, list, "");
        return list;
    }

        private void generate(int level, int max, ArrayList<String> list, String str) {
            //terminal
            if (level == max) {
                if (valid(str)) {
                    list.add(str);
                }
                return;
            }
            //processing
            generate(level+1,max,list,str+"(");
            generate(level+1,max,list,str+")");
        }

        private boolean valid(String str) {
            char[] chars = str.toCharArray();
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if ('(' == (chars[i])) {
                    stack.push("(");
                } else {
                    if (stack.empty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
            return stack.empty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

