package leetcode.editor.cn.string;/**
 * <p>
 * TODO
 * </p>
 *
 * @author shuhao.chen
 * @since 2022/4/11
 */

import java.util.Arrays;

/**
 * 项目名称: My-LeetCode
 * @ClassName MyKmp
 * @Description
 * @Author shuhao.chen    @Date 2022/4/11 16:06
 */
public class MyKmp {
    public static void main(String[] args) {
       /* String s = "CABAABABA";
        String pattern = "ABAB";//模式串*/
        String s = "aaaaa";
        String pattern = "bba";
        int[] prefix = new int[pattern.length()];
        getPrefixTable(pattern,prefix);
        kmpSearch(s,pattern,prefix);
    }
    //public static void main(String[] args) {
    //    int[] prefix = {1, 3, 3, 4};
    //    movePrefixTable(prefix);
    //    System.out.println(Arrays.toString(prefix));
    //}

    private static void kmpSearch(String s, String pattern, int[] prefix) {
        int slength = s.length();
        int plength = pattern.length();
        int i = 0,j = 0;
        while (i < slength) {
            //如果当前j已经到了限定的长度,说明已经完全匹配上,并且当前2个字符相等
            if (j == plength - 1 && s.charAt(i) == pattern.charAt(j)) {
                System.out.println("找到了当前索引"+(i-j));
                //移动当前
                j = prefix[j];
            }
            if (s.charAt(i) == pattern.charAt(j)) {//检测到字符相同
                i++;j++;
            } else {//如果字符不相同
                //移动当前
                j = prefix[j];
                //因为我们默认是-1开头,所以找到开头都没有匹配上 i++;j++
                if (j == -1) {
                    i++;j++;
                }
            }
        }
    }

    private static void getPrefixTable(String pattern, int[] prefix) {
        int length = pattern.length();
        prefix[0] = 0;
        int i = 1;//第一位默认是0,从第一位开始遍历
        int k = 0;
        while (i < length) {
            //当前的字符和前一位对称的字符是否相等
            if (pattern.charAt(i) == pattern.charAt(k)) {
                //2个字符都往后移动一位,并将当前前缀表更新
                k++;
                prefix[i]=k;
                i++;
            } else {
                //如果是不相等的情况 判断k是否大于0 k大于 就可以继续往前找公共前缀相匹配
                if (k>0) {
                    k = prefix[k - 1];
                } else {
                    //k=0,说明没有找到子对称的结构,把当前的前缀数组置为0,i++ 类似又重复上面k=0 i=1的步骤
                    prefix[i]=0;
                    i++;
                }
            }
        }
        movePrefixTable(prefix);
    }

    private static void movePrefixTable(int[] prefix) {
        for (int i = prefix.length-1;i>=1;i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }
}
