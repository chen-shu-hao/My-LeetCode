package leetcode.editor.cn;
//n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
// 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
//
//
//
// 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例 1：
//
//
//输入：n = 4
//输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
//解释：如上图所示，4 皇后问题存在两个不同的解法。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[["Q"]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 9
//
//
//
// Related Topics 数组 回溯
// 👍 1234 👎 0

//java:N 皇后
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueens{
    public static void main(String[] args){
        Solution solution = new NQueens().new Solution();
        System.out.println(solution.solveNQueens(4));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private  LinkedHashSet<Integer> columns = new LinkedHashSet<>();
        private HashSet<Integer> pie = new HashSet<>();
        private   HashSet<Integer> na = new HashSet<>();
        public List<List<String>> solveNQueens(int n) {
            //先判断特殊情况
            List<List<String>> allResults = new ArrayList<>();
            if (n <= 0) {
                return allResults;
            }
            dfs(0,n,allResults);
            return allResults;
        }
        private void dfs(int level,int n,List<List<String>> allResults) {
            //terminal 如果到了n说明之前的所有都找到了
            if (level == n) {
                //说明所有行中都找到了皇后
                allResults.add(draw(columns));
            }

            for (int i=-0;i<n;i++) {
                if (columns.contains(i)||pie.contains(level+i)||na.contains(level-i)) continue;
                columns.add(i);
                pie.add(level+i);
                na.add(level-i);
                dfs(level+1,n,allResults);
                //reserve
                columns.remove(i);
                pie.remove(level+i);
                na.remove(level-i);
            }
        }

        private List<String> draw(LinkedHashSet<Integer> columns) {
            List<String> strs = new ArrayList<>();
            for (Integer column : columns) {
                strs.add(IntStream.range(0, columns.size()).mapToObj(i -> i == column ? "Q":".").collect(Collectors.joining("")));
            }
            return strs;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

