package leetcode.editor.cn;
//编写一个程序，通过填充空格来解决数独问题。 
//
// 数独的解法需 遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 
//
// 
// 
// 
// 示例 1： 
//
// 
//输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5","."
//,".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".","."
//,"3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"
//],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],["
//.",".",".",".","8",".",".","7","9"]]
//输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"
//],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["
//4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","
//6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","
//5","2","8","6","1","7","9"]]
//解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
//
//
// 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 题目数据 保证 输入数独仅有一个解 
// 
// 
// 
// 
// Related Topics 数组 回溯 矩阵 
// 👍 1199 👎 0

//java:解数独

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class P37_SudokuSolver {
    public static void main(String[] args) {
        Solution solution = new P37_SudokuSolver().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private boolean[][] rows = new boolean[9][9];
        private boolean[][] cols = new boolean[9][9];
        private boolean[][] grids = new boolean[9][9];
        private List<int[]> emptys = new LinkedList<>();

        public void solveSudoku(char[][] board) {
            if (board.length == 0) {
                return;
            }
            //先将不为空的.转换为true
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if ('.' != board[i][j]) {
                        int currentNum = board[i][j] - '1';
                        rows[i][currentNum] = true;
                        cols[j][currentNum] = true;
                        grids[i / 3 * 3 + j / 3][currentNum] = true;
                    } else {
                        //将为空的数据保存起来
                        emptys.add(new int[]{i, j});
                    }
                }
            }
            dfs(0, board);
        }

        private boolean dfs(int level, char[][] board) {
            if (level == emptys.size()) {
                return true;
            }
            //遍历每个空节点
            int[] ints = emptys.get(level);
            int row = ints[0];
            int col = ints[1];
            int gridIndex = row / 3 * 3 + col / 3;
            //从1到9开始尝试
            for (char j = '1'; j <= '9'; j++) {
                int currentNum = j - '1';
                if (rows[row][currentNum] || cols[col][currentNum] || grids[gridIndex][currentNum]) continue;
                rows[row][currentNum] = true;
                cols[col][currentNum] = true;
                grids[gridIndex][currentNum] = true;
                board[row][col] = j;
                if (dfs(level + 1, board)) return true;
                //resever
                rows[row][currentNum] = false;
                cols[col][currentNum] = false;
                grids[gridIndex][currentNum] = false;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}


