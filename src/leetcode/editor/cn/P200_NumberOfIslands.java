package leetcode.editor.cn;
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 
// 👍 1631 👎 0

//java:岛屿数量

import java.util.List;

public class P200_NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new P200_NumberOfIslands().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][] dirs = {{1, 0},{0, 1}};
        int cols = 0;
        public int numIslands(char[][] grid) {
            int rows = grid.length;
            if (rows <= 0) {
                return 0;
            }
            cols = grid[0].length;
            int spaces = 0;//空地数量, 岛屿数量 = 并查集count - space;
            //构建并查集,每个位置填充当前索引值
            UnionFind unionFind = new UnionFind(rows * cols);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if ('0' == grid[i][j]) {
                        spaces++;
                    } else {
                        //向右向下找即可(因为下次进入的左节点也是和当前并查集结合组成同个岛屿)
                        for (int[] dir : dirs) {
                            int newI = i + dir[0], newJ = j + dir[1];
                            if (newI < rows && newI < cols && grid[newI][newJ] == '1') {
                                unionFind.union(getIndex(i,j),getIndex(newI,newJ));
                            }
                        }
                    }
                }
            }
            return unionFind.getCount() - spaces;
        }
        //二维坐标 (x,y) 可以转换成 x * n + y 这个数（m 是棋盘的行数，n 是棋盘的列数），敲黑板，这是将二维坐标映射到一维的常用技巧
        private int getIndex(int i, int j) {
            return i*cols + j;
        }
       /*
        public int numIslands(char[][] grid) {
            int rows = grid.length;
            if (rows <= 0) {
                return 0;
            }
            int cols = grid[0].length;
            int isLandsNum = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    //判断当前节点是否是陆地
                    if ('1' == grid[i][j]) {
                        dfs(grid, i, j);
                        isLandsNum++;
                    }
                }
            }
            return isLandsNum;
        }
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        private void dfs(char[][] grid, int i, int j) {
            //如果当前位置是水,停止搜索
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
            if ('0' == grid[i][j]) {
                return;
            }
            for (int[] dir : dirs) {//需要保证此次循环将所有的位置都置为水
                int newI = i + dir[0], newJ = j + dir[1];
                //将当前坐标先置为水
                grid[i][j] = '0';
                dfs(grid, newI, newJ);

            }
        }*/
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

