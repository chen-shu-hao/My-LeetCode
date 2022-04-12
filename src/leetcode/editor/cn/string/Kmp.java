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
 * @ClassName Kmp
 * @Description
 * @Author shuhao.chen    @Date 2022/4/11 11:25
 */
public class Kmp {
    //前缀表后移一位，第一个值设为-1
    public static void movePrefixTable(int[] prefix) {
        int i;
        int n = prefix.length;
        for (i = n - 1; i > 0; i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }
    //获得模式串的前缀表
    public static void getPrefixTable(String pattern, int[] prefix) {
        prefix[0] = 0;//前缀表第一位永远是0
        int k = 0;//k为上一个字符的对称程度，如果k=0说明不再有子对称
        int i = 1;//因为第一位的prefix永远是0，所以从第二位开始计算
        int n = pattern.length();//模式串的长度
        while (i < n) {
            if (pattern.charAt(k) == pattern.charAt(i)) {//找到了这个子对称，或者是直接继承了前面的对称性，这两种都在前面的基础上++
                k++;
                prefix[i] = k;
                i++;
            } else {
                if (k > 0) {
                    k = prefix[k - 1];//寻找更小的对称程度
                } else {//如果遍历了所有子对称都无效，说明这个新字符不具有对称性，清0
                    prefix[i] = k;
                    i++;
                }
            }
        }
        System.out.println(Arrays.toString(prefix));
        movePrefixTable(prefix);//得到最终的前缀表
        System.out.println(Arrays.toString(prefix));
    }

    //kmp模式匹配算法
    public static void kmp(String S, String T, int pos) {
        int i = pos;//i用于存储主串S中当前位置的下标值，从pos位置开始匹配
        int j = 0;//j用于存储子串T中当前位置的下标值
        int[] prefix = new int[T.length()];
        getPrefixTable(T, prefix);

        while (i < S.length()) {//当i小于（S的长度 - 1）并且j小于（T的长度 - 1），循环继续
            if (j == T.length() - 1 && S.charAt(i) == T.charAt(j)) {
                System.out.println("找到匹配字符index：" + (i - j));
                //如果当前
                j = prefix[j];
            }

            if (S.charAt(i) == T.charAt(j)) {//检测到有相等的字符，就继续
                ++i;
                ++j;
            } else {
                j = prefix[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }
    }
    public static void main(String[] args) {

        String str1 = "真da真真真真帅 韩帅比真帅";
        String str2 = "真";
        kmp(str1, str2, 0);


        String str3 = "真真a真真真帅 韩帅比真帅";
        String str4 = "真真";
        kmp(str3, str4, 0);

        String str5 = "ABABABAACABAACABAAA";
        String str6 = "ABAACABAA";
        kmp(str5, str6, 0);
    }

}
