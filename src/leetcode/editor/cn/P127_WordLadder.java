package leetcode.editor.cn;
//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s
//2 -> ... -> sk： 
//
// 
// 每一对相邻的单词只差一个字母。 
// 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 
//中的 单词数目 。如果不存在这样的转换序列，返回 0 。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 982 👎 0

//java:单词接龙

import com.sun.org.apache.xpath.internal.operations.Plus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class P127_WordLadder {
    public static void main(String[] args) {
        Solution solution = new P127_WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            //判断是否包含结尾字符串
            if (!wordSet.contains(endWord)) {
                return 0;
            }
            wordSet.remove(beginWord);
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            //因为不同单词之间是双向的关系(图),不能折回访问,不然必然不是最短的路径
            Set<String> visited = new HashSet<>();
            visited.add(beginWord);
            //起点算第一步
            int step = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String currentWord = queue.poll();
                    //判断当前单词和wordSet中的单词替换一个字符串是否能得到endWord,如果能就直接返回step+1
                    if (canGetEndWord(currentWord, endWord, visited, wordSet, queue)) {
                        //只有在这里才是找到了
                        return step + 1;
                    }
                }
                step++;
            }
            //说明没找到
            return 0;
        }

        private boolean canGetEndWord(String currentWord, String endWord, Set<String> visited, Set<String> wordSet, Queue<String> queue) {
            char[] charArr = currentWord.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                char originChar = charArr[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    if (originChar == j) {
                        continue;
                    }
                    charArr[i] = j;
                    String nextWord = String.valueOf(charArr);
                    if (wordSet.contains(nextWord)) {
                        if (endWord.equals(nextWord)) {
                            return true;
                        }
                        if (!visited.contains(nextWord)) {
                            queue.offer(nextWord);
                            visited.add(nextWord);
                        }
                    }
                }
                charArr[i] = originChar;
            }
            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

