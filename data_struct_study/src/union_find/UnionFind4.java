package union_find;


/**
 * 基于 rank（深度排名，并不完全表示数的深度） 的优化，比基于 size 的优化要更合理，合并后得到的 h 在某些情况下要更小。
 */
public class UnionFind4 implements UF {

    /**
     * parent[i] 表示第i个元素所属集合中的根节点。
     */
    private int[] parent;

    /**
     * sz[i] 表示以i为根的集合的深度
     */
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];

        // 每一个元素都是一个单独的集合，也是一棵树的根节点，特征为自己指向自己
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
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

        // 将元素深度低的集合合并到元素深度高的集合上
        if (rank[p] < rank[q]) {
            parent[pRoot] = qRoot;
        } else if (rank[p] > rank[q]){
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
