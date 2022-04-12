package leetcode.editor.cn;
//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。 
//
// 实现 LFUCache 类： 
//
// 
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。 
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capac
//ity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。 
// 
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。 
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "g
//et"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3 
//
// 
//
// 提示： 
//
// 
// 0 <= capacity <= 104 
// 0 <= key <= 105 
// 0 <= value <= 109 
// 最多调用 2 * 105 次 get 和 put 方法 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 516 👎 0

//java:LFU 缓存

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class P460_LfuCache {
    //public static void main(String[] args){
    //    Solution solution = new P460_LfuCache().new Solution();
    //}
    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {
        //需要3个map kv kf fkList
        private Map<Integer, Integer> kvMap;
        private Map<Integer, Integer> kfMap;
        private Map<Integer, LinkedHashSet<Integer>> freqKeysMap;
        //记录当前的
        private int minFreqs;
        private int capacity;

        public LFUCache(int capacity) {
            this.kvMap = new HashMap<>();
            this.kfMap = new HashMap<>();
            this.freqKeysMap = new HashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer value = kvMap.get(key);
            if (null == value) {
                return -1;
            }
            //增加freq的使用数
            addFreq(key);
            return value;
        }

        public void put(int key, int value) {
            if (this.capacity <= 0) return;
            if (kvMap.containsKey(key)) {
                //修改value的值即可
                kvMap.put(key, value);
                //使用次数+1
                addFreq(key);
                return;
            }
            //判断当前的容量
            if (kvMap.size() == capacity) {
                removeLeastFrequen();
            }
            addNode(key, value);
        }

        private void addNode(int key, int value) {
            kvMap.put(key, value);
            kfMap.put(key, 1);
            freqKeysMap.putIfAbsent(1, new LinkedHashSet<>());
            freqKeysMap.get(1).add(key);
            //插入新key后的最小freq = 1
            this.minFreqs = 1;
        }

        //只做对freq+1的操作
        private void addFreq(int key) {
            Integer freq = kfMap.get(key);
            kfMap.put(key, freq + 1);
            //先获取原来序列的列表进行删除,再添加到最新的列表中
            freqKeysMap.get(freq).remove(key);
            freqKeysMap.putIfAbsent(freq + 1, new LinkedHashSet<>());
            freqKeysMap.get(freq + 1).add(key);
            //防止put不进去
            if (freqKeysMap.get(freq).isEmpty()) {
                freqKeysMap.remove(freq);
                //如果这个freq恰好是minfreq,更新minFreq
                if (freq == this.minFreqs) {
                    this.minFreqs++;
                }
            }
        }

        private void remove(int key) {
            Integer freq = kfMap.get(key);
            kvMap.remove(key);
            kfMap.remove(key);
            freqKeysMap.get(freq).remove(key);
            //防止put不进去
            if (freqKeysMap.get(freq).isEmpty()) {
                freqKeysMap.remove(freq);
                //如果这个freq恰好是minfreq,更新minFreq
                if (freq == this.minFreqs) {
                    this.minFreqs--;
                }
            }
        }

        private void removeLeastFrequen() {
            LinkedHashSet<Integer> set = freqKeysMap.get(minFreqs);
            Integer next = set.iterator().next();
            //第一个节点就是最开始的节点
            set.remove(next);
            if (set.isEmpty()) {
                freqKeysMap.remove(minFreqs);//此处不需要更新最小值 因为新增时最小值肯定会被重置为1
            }
            kfMap.remove(next);
            kvMap.remove(next);
        }
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

