/**
 * className UnionFind4
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/1 19:57
 */
public class UnionFind4 implements UF{

    private int[] parent;
    /**
     * 用于记录每个树的大小
     * rank[i]表示以i为根的树的高度
     */
    private int[] rank;

    public UnionFind4(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
            rank[i] = 1;
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
         * 让高度小的树的根节点指向高度大的树的根节点
         * 合并两个高度相同的树时要维护rank数组
         */
        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}