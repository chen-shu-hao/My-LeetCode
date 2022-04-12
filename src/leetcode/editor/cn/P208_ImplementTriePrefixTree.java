package leetcode.editor.cn;
//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。 
//
// 请你实现 Trie 类： 
//
// 
// Trie() 初始化前缀树对象。 
// void insert(String word) 向前缀树中插入字符串 word 。 
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。 
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length, prefix.length <= 2000 
// word 和 prefix 仅由小写英文字母组成 
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次 
// 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 1129 👎 0

//java:实现 Trie (前缀树)

import java.util.List;

public class P208_ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Trie {
        //2个变量  Trie数组 isEnd 判断是否包含完整的字符串
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            //默认长度26位
            this.children = new Trie[26];
            isEnd = false;
        }

        public void insert (String world) {
            //当前node会一直变换
            Trie node = this;
            if (null==world || world.length() == 0) {
                return;
            }
            for (int i = 0; i < world.length(); i++) {
                int index = world.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                //将当前子节点设为下一次父节点
                node = node.children[index];
                System.out.println("this:"+this);
                System.out.println("node:"+node);
            }
            //设置最后一个节点为最后
            node.isEnd = true;
        }

        public boolean search(String world) {
            Trie trie = this.searchPrefix(world);
            return trie != null && trie.isEnd;
        }

        public boolean startsWith(String prefix) {
            Trie trie = searchPrefix(prefix);
            return trie != null;
        }

        public Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                int index = prefix.charAt(i) - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

