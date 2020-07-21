package union_find;


/**
 * 基于 size 的优化，避免形成类似链表的结构，导致 O（h）过大。
 */
public class UnionFind3 implements UF {

    /**
     * parent[i] 表示第i个元素所属集合中的根节点。
     */
    private int[] parent;

    /**
     * sz[i] 表示以i为根的集合中的元素个数
     */
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        // 每一个元素都是一个单独的集合，也是一棵树的根节点，特征为自己指向自己
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {

        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound~");
        }

        // 根节点：parent[p] == p
        if (parent[p] != p) {
            p = parent[p];
        }

        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 将元素个数少的集合合并到元素个数多的集合上
        if (sz[p] < sz[q]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
