package leetcode.editor.cn;
//编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。 
//
// 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = ['h','e','l','l','o']
//输出：["o","l","l","e","h"]
// 
//
// 示例 2： 
//
// 
//输入：s = ["H","a","n","n","a","h"]
//输出：["h","a","n","n","a","H"] 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 105 
// s[i] 都是 ASCII 码表中的可打印字符 
// 
// Related Topics 递归 双指针 字符串 
// 👍 559 👎 0

//java:反转字符串
import java.util.List;
public class P344_ReverseString{
    public static void main(String[] args){
        Solution solution = new P344_ReverseString().new Solution();
        solution.reverseString(new char[]{'h','e','l','l','o'});
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public void reverseString(char[] s) {
            if (s == null || s.length == 0) return;
           //简单的双指针
            int length = s.length;
           /* for (int left=0,right=length-1;left < right;left++,right--) {
                swap(s, left, right);
            }*/
           int left = 0;
           int right = length - 1;
           while (left < right) {
               char temp = s[left];
               s[left++] = s[right];
               s[right--] = temp;
           }
        }
   /* public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int length = s.length;
        for (int i = 0; i < length/2; i++) {
            int index= length - i - 1;
            swap(s, i, index);
        }
    }*/

        private void swap(char[] s, int i, int index) {
            char temp = s[i];
            s[i] = s[index];
            s[index] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}

