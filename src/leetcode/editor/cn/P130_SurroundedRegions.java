package leetcode.editor.cn;
//给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充
//。
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：board = [['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X'
//,'X']]
//输出：[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
//解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都
//会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
// 
//
// 示例 2： 
//
// 
//输入：board = [['X']]
//输出：[['X']]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 200 
// board[i][j] 为 'X' 或 'O' 
// 
// 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 760 👎 0

//java:被围绕的区域
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;

public class P130_SurroundedRegions{
    public static void main(String[] args){
        Solution solution = new P130_SurroundedRegions().new Solution();
        solution.solve(new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int rows = 0;
    int cols = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
     public void solve(char[][] board) {//搜索所有的O节点 将边界上的O组成的圈子与dummy节点连接 和 内部的圈子分隔开
         rows = board.length;
         cols = board[0].length;
         //构建并查集(将二维数组转换为一维数组) 多出一个节点为dummy虚拟节点
         UnionFind unionFind = new UnionFind(rows * cols + 1);
         int dummy = rows * cols;
         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                 if ('O' == board[i][j]) {
                     if (i == 0 || j == 0 || i == rows-1 || j == cols -1) {
                        unionFind.union(getIndex(i,j),dummy);
                     } else {
                         for (int[] dir : dirs) {
                             int newI = i+dir[0],newJ = j+dir[1];
                             if (newI>=0 && newJ>=0 && newI < rows && newJ < cols && 'O' == board[newI][newJ]) {
                                 unionFind.union(getIndex(i,j),getIndex(newI,newJ));
                             }
                         }
                     }
                 }
             }
         }

         for (int i = 0; i < rows; i++) {
             for (int j = 0; j < cols; j++) {
                if (!unionFind.isConnected(getIndex(i,j),dummy)) {
                    board[i][j] = 'X';
                }
             }
         }
         System.out.println(Arrays.deepToString(board));
     }

        private int getIndex(int i, int j) {
            return i * cols + j;
        }

    /*public void solve(char[][] board) {
        //从四边的边界遍历,寻找与o连接的o,将他们都转换为#
        // 完结之后将#转为o,再将o转为x
        rows = board.length;
        cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows-1 || j == cols -1) {
                    dfs(board, i, j,visited);
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ('#' == board[i][j]) {
                    board[i][j] = 'O';
                } else if ('O' == board[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }*/

        private void dfs(char[][] board, int i, int j, boolean[][] visited) {
            if (i < 0 || j < 0 || i >=rows || j >= cols || board[i][j] == 'X') {
                return;
            }
            if (visited[i][j]) {
                return;
            }
            visited[i][j] = true;
            //说明当前是O
            board[i][j] = '#';
            for (int[] dir : dirs) {
                int newI = i + dir[0], newJ = j + dir[1];
                dfs(board, newI , newJ,visited);
            }
        }
        class UnionFind {
            //当前集合的个数
            private int setCount;
            private int[] parent;
            //n为节点总数
            public UnionFind(int n) {
                //初始集合个数为n
                this.setCount = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    //将当前节点的父节点指向自己
                    parent[i] = i;
                }
            }

            public void union(int p,int q) {
                int pRoot = find(p);
                int qRoot = find(q);
                if (pRoot == qRoot) {
                    //如果2个节点相等,说明2个已经在同一个集合
                    return;
                }
                //2个不在同一个集合 将proot父节点改为qroot
                parent[pRoot] = qRoot;
                //集合个数减一
                setCount--;
            }

            public int find(int p) {
                //只有当parent[p]=p;
                while (parent[p]!=p) {
                    //将当前p节点的父节点改为当前的p节点的爷爷节点(此处在做路径压缩,将原本树的路径压缩一半,方便之后用)
                    parent[p] = parent[parent[p]];
                    //将当前新的父节点赋值给当前节点
                    p = parent[p];
                }
                return p;
            }
            public boolean isConnected(int p,int q) {
                return find(p) == find(q);
            }
            public int getCount() {
                return this.setCount;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

