package leetcode.editor.cn;
//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 104 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 104 
// 
// Related Topics 数组 排序 
// 👍 1419 👎 0

//java:合并区间
import java.util.Arrays;
import java.util.List;
public class P56_MergeIntervals{
    public static void main(String[] args){
        Solution solution = new P56_MergeIntervals().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        //先按照对数组的第一个元素进行排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int[][] results  = new int[intervals.length][intervals[0].length];
        //遍历intervars
        int index = -1;//实现了每次都和上一个数组比较
        for (int[] interval : intervals) {
            //当前是第一次空数组或者当前的interva[0]比上一个的末尾要大,说明2个数组不重合
             if (index == -1 || interval[0] > results[index][1]) {
                 results[++index] = interval;
             } else {//这里数组会变少
                 results[index][1] = Math.max(interval[1],results[index][1]);
             }
            //否则取interval[1]和当前末尾的较大值更新当前末尾
        }
        System.out.println(Arrays.deepToString(results));
        return Arrays.copyOf(results, index+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

