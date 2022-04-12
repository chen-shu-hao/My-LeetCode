package leetcode.editor.cn;
//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [['o','a','a','n'],['e','t','a','e'],['i','h','k','r'],['i','f','l'
//,'v']], words = ['oath','pea','eat','rain']
//输出：['eat','oath']
// 
//
// 示例 2： 
//
// 
//输入：board = [['a','b'],['c','d']], words = ['abcb']
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 
// 👍 637 👎 0

//java:单词搜索 II
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class P212_WordSearchIi{
    public static void main(String[] args){
        Solution solution = new P212_WordSearchIi().new Solution();
        solution.findWords(new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}}, new String[]{"oath", "pea", "eat", "rain"});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //先定义向量坐标 一个个小盒子
        private int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        public List<String> findWords(char[][] board, String[] words) {
            //将当前现有字符构造当前字典数
            Trie trie = new Trie();
            for (String word : words) {
                trie.insert(word);
            }
            //因为不同路径可能出现同样的字符,所以要去重
            Set<String> results = new HashSet<>();
            //遍历2层向量坐标,渠道所有坐标点作为起点的字符串
            for (int i=0;i<board.length;i++) {
                for (int j=0;j<board[0].length;j++) {
                    dfs(board,trie,i,j,results);
                }
            }
            return new ArrayList<>(results);
        }
        private void dfs(char[][] board,Trie now,int i1,int j1,Set<String> results) {
            //判断当前节点的子节点中是否包含当前字符
            if (!now.children.containsKey(board[i1][j1])) {
                return;
            }
            //更新当前节点
            char c = board[i1][j1];
            now = now.children.get(c);
            //判断当前是否是尾节点
            if (!"".equals(now.word)) {
                results.add(now.word);
            }
            //防止节点重复遍历
            board[i1][j1] = '#';
            for (int[] dir : dirs) {
                int i2 = i1 + dir[0],j2 = j1 + dir[1];
                if (i2>=0 && i2<board.length && j2>=0 && j2<board[0].length) {
                    dfs(board,now,i2,j2,results);
                }
            }
            //reserve
            board[i1][j1] = c;
        }
    }

    class Trie {
        String word;
        Map<Character,Trie> children;
        boolean isWord;

        public Trie() {
            this.word = "";
            this.children = new HashMap<>(26);
        }

        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c,new Trie());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}

