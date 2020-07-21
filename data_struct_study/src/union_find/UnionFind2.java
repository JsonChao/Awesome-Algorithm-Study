package union_find;

public class UnionFind2 implements UF {

    /**
     * parent[i] 表示第i个元素所属集合中的根节点。
     */
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        // 每一个元素都是一个单独的集合，也是一棵树的根节点，特征为自己指向自己
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
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

        parent[pRoot] = q;
    }
}
