//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 104 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 哈希表 字符串 排序 
// 👍 453 👎 0

package com.company.leetcode.editor.cn;
//java:有效的字母异位词
public class P242ValidAnagram{
    public static void main(String[] args){
        Solution solution = new P242ValidAnagram().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        //寻找字母异位词
        if (s.isEmpty() || t.isEmpty()) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Map<String, Integer> smaps = new HashMap<>();
        Map<String, Integer> tmaps = new HashMap<>();
        for (int i=0;i<sCharArray.length;i++) {
            //先判断map中是否有key 有就加1 没有复制0
            char c = sCharArray[i];
            Object num = smaps.get();
            if (null == num) {
                smaps.put(c, 1);
            }else {
                smaps.put(c, Integer.valueOf(num) + 1);
            }
        }

        for (int i=0;i<tCharArray.length;i++) {
            //先判断map中是否有key 有就加1 没有复制0
            char c = tCharArray[i];
            Object num = tmaps.get();
            if (null == num) {
                tmaps.put(c, 1);
            }else {
                tmaps.put(c, Integer.valueOf(num) + 1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

