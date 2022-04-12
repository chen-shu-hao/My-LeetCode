package leetcode.editor.cn;
//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1834 👎 0

//java:零钱兑换

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class P322_CoinChange1 {
    public static void main(String[] args) {
        Solution solution = new P322_CoinChange1().new Solution();
        System.out.println(solution.coinChange(new int[]{1,2,5}, 100));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int coinChange(int[] coins, int amount) {
            //BFS 找到第一个算出来等于0的等级
            if (amount==0) {
                return 0;
            }
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(amount);
            // 排序是为了加快广度优先遍历过程中，对硬币面值的遍历，起到剪枝的效果
            Arrays.sort(coins);
            int level = 0;
            //判断队列是否为空,一次性将队列里所有的数据都取出来
            while (!queue.isEmpty()) {
                int queueSize = queue.size();
                for (int j=0;j<queueSize;j++) {
                    Integer currentAmount = queue.poll();
                    for (int i=0;i<coins.length;i++) {
                        int next = currentAmount - coins[i];
                        if (next ==0) {
                            return level+1;
                        }
                        if (next <0){
                            break;
                        }
                        //图 判断是否已经访问过
                        if (!visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
                level++;
            }
            return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}

