/**
 * className UnionFind2
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/1 16:17
 */
public class UnionFind2 implements UF{

    private int[] parent;

    public UnionFind2(int size){
        parent = new int[size];

        for (int i = 0; i < size; i++){
            parent[i] = i;
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

        parent[pRoot] = qRoot;
    }
}
