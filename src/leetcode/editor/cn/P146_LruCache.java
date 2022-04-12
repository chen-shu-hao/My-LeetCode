package leetcode.editor.cn;
//请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。 
//
// 实现 LRUCache 类： 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 ke
//y-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
// 
// 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 105 
// 最多调用 2 * 105 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 2079 👎 0

//java:LRU 缓存


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P146_LruCache {
    //public static void main(String[] args){
    //    Solution solution = new P146_LruCache().new Solution();
    //}
    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        private Map<Integer, Node> map;

        private DoubleList cache;

        private int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.cache = new DoubleList();
        }

        public int get(int key) {
            Node curNode = map.get(key);
            if (curNode == null) {
                return -1;
            }
            makeRecently(key);
            return curNode.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                removeKey(key);
            }
            //容量超出 删除尾结点
            if (cache.size() == capacity) {
                removeLeastRecently();
            }
            addRecently(key,value);
        }

        public void makeRecently(int key) {
            Node curNode = map.get(key);
            cache.remove(curNode);
            cache.addLast(curNode);
        }

        public void addRecently(int key, int value) {
            //将当前节点放到尾结点
            Node node = new Node(key, value);
            cache.addLast(node);
            map.put(key, node);
        }

        public void removeKey(int key) {
            Node curNode = map.get(key);
            cache.remove(curNode);
            map.remove(key);
        }

        public void removeLeastRecently() {
            Node node = cache.removeFirst();
            map.remove(node.key);
        }

    }

    class DoubleList {
        //头尾的虚拟节点
        private Node head, tail;
        //链表长度
        private int size;

        public DoubleList() {
            //初始化链表指针
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public void addLast(Node node) {
            //tail需要一直是尾节点
            node.next = tail;
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;//尾节点的前向指针最后改变
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        //删除链表中第一个节点,并返回该节点
        public Node removeFirst() {
            //不能删除尾节点
            Node first = head.next;
            if (first == tail) {
                return null;
            }
            //删除最近最少使用的节点
            remove(first);
            return first;
        }

        public int size() {
            return this.size;
        }
    }

    //基础的节点
    class Node {
        public int key;
        public int value;
        public Node prev,next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

