/**
 * className UnionFind3
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/1 19:25
 */
public class UnionFind3 implements UF{

    private int[] parent;
    /**
     * 用于记录每个树的大小
     * sz[i]表示以i为根的集合中元素的个数
     */
    private int[] sz;

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查找元素p对应集合编号
     * @param p
     * @return
     */
    private int find(int p){

        if (p < 0 && p >= parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }

    /**
     * 查询元素p，q是否属于一个集合
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p，q所属的集合
     * O(h)
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot){
            return;
        }

        /**
         * 让节点少的树的根节点指向节点多的树的根节点
         */
        if (sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}
