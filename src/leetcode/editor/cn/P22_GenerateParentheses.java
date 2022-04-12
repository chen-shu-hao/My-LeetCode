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
// 👍 2514 👎 0

//java:括号生成
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class P22_GenerateParentheses{
    public static void main(String[] args){
        Solution solution = new P22_GenerateParentheses().new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<String> generateParenthesis(int n) {
            /*取出开头最左边正常的一个括号
             将最左边的括号和中间右边括号取出来,剩下的括号要么在中间,要么在右边 所以 dp[n-1] = dp[p] + dp[q];
             subproblem
             动态数组  dp[n-1]
             动态方程  dp[n-1] = dp[p] + dp[q]
             */
            List<List<String>> dp = new LinkedList<>();
            //动态规划方程初始化
            dp.add(Arrays.asList(""));
            dp.add(Arrays.asList("()"));
            //第一层遍历括号中间的可能出现的对数
            for (int i = 2; i <= n; i++) {
                LinkedList<String> list = new LinkedList<>();
                for (int j = 0; j < i; j++) {
                    //取q可能出现的数目
                    List<String> p = dp.get(j);
                    List<String> q = dp.get(i - 1 - j);
                    //遍历左括号和右括号可能出现的数量
                    for (String s1 : p) {
                        for (String s2 : q) {
                            list.add("(" + s1 + ")" + s2);
                        }
                    }
                }
                dp.add(list);
            }
            return dp.get(n);
        }
      /*  public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            generate(0, 0,n, list, "");
            return list;
        }*/
        private void generate(int left, int right,int n, List<String> list, String str) {
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

