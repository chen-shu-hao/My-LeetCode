package leetcode.editor.cn;/**
 * <p>
 * TODO
 * </p>
 *
 * @author shuhao.chen
 * @since 2022/4/1
 */

/**
 * 项目名称: My-LeetCode
 * @ClassName UnionFind
 * @Description
 * @Author shuhao.chen    @Date 2022/4/1 13:43
 */
public class UnionFind {
    //当前集合的个数
    private int setCount;
    private int[] parent;
    //n为节点总数
    public UnionFind(int n) {
        //初始集合个数为n
        this.setCount = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            //将当前节点的父节点指向自己
            parent[i] = i;
        }
    }

    public void union(int p,int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            //如果2个节点相等,说明2个已经在同一个集合
            return;
        }
        //2个不在同一个集合 将proot父节点改为qroot
        parent[pRoot] = qRoot;
        //集合个数减一
        setCount--;
    }

    public int find(int p) {
        //只有当parent[p]=p;
        while (parent[p]!=p) {
            //将当前p节点的父节点改为当前的p节点的爷爷节点(此处在做路径压缩,将原本树的路径压缩一半,方便之后用)
            parent[p] = parent[parent[p]];
            //将当前新的父节点赋值给当前节点
            p = parent[p];
        }
        return p;
    }

    public boolean isConnected(int p,int q) {
        return find(p) == find(q);
    }

    public int getCount() {
        return this.setCount;
    }
}
