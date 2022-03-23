package leetcode.editor.cn.recursion;
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
public class GenerateParentheses1 {
    public static void main(String[] args){
        Solution solution = new GenerateParentheses1().new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        generate(0, 0,n, list, "");
        return list;
    }

        private void generate(int left, int right,int n, ArrayList<String> list, String str) {
            //terminal
            if (left == n && right == n) {
                list.add(str);
                return;
            }
            //processing
            //左边的括号可以一直加，右边的括号要小于左边的括号
            if (left<n) {
                generate(left+1,right,n,list,str+"(");
            }//因为left已经小于了n，所以这里不要判断right<n
            if (right < left) {
                generate(left,right+1,n,list,str+")");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

