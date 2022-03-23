package leetcode.editor.cn;
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出：3
//解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
// 
//
// 示例 2： 
//
// 
//输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出：5
//解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], p = 1, q = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [2, 105] 内。 
// -109 <= Node.val <= 109 
// 所有 Node.val 互不相同 。 
// p != q 
// p 和 q 均存在于给定的二叉树中。 
// 
// Related Topics 树 深度优先搜索 二叉树 
// 👍 1633 👎 0

//java:二叉树的最近公共祖先
import java.util.List;
public class LowestCommonAncestorOfABinaryTree{
    public static void main(String[] args){
        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //termina
        if (null == root) {
            return null;
        }
        //递归中,不管是匹配上哪一个都直接返回,因为左边节点和右边节点都会做一次深度全遍历
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        //递归左子树的全部节点,找到左边的第一个出现的p点或者是q点
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        //递归右子树的全部节点,找到右边的第一个出现的p点或者是q点
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        //说明2个节点分布在左右两边
        if (null != left && null != right) {
            return root;
        }
        if (null == left) return right;
        if (null == right) return left;
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

