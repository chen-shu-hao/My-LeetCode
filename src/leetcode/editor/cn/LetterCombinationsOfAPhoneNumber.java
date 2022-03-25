package leetcode.editor.cn;
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1796 👎 0

//java:电话号码的字母组合
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args){
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        private Map<Character, String> map = new HashMap(){
            {
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};

    public List<String> letterCombinations(String digits) {
        List<String> strs = new ArrayList<>();
        if (digits.length() == 0) {
            return strs;
        }
        recursionLetter(0, digits,new StringBuilder(), strs);
        return strs;
    }

        private void recursionLetter(int level, String digits,StringBuilder str, List<String> strs) {
            if (level == digits.length()) {
                strs.add(str.toString());
                return;
            }
            //procesing
            char[] letters = map.get(digits.charAt(level)).toCharArray();
            for (int i = 0; i < letters.length; i++) {
                str.append(letters[i]);
                recursionLetter(level+1,digits,str,strs);
                //reserve 恢复状态,因为对象要被复用
                str.deleteCharAt(str.length() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

