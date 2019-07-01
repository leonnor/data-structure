/**
 * className UnionFind1
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/1 15:17
 */
public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size){

        id = new int[size];
        /**
         * 假设每个数据都属于不同的集合
         */
        for (int i = 0; i < id.length; i++){
            id[i] = i;
        }
    }
    @Override
    public int getSize() {
        return id.length;
    }

    /**
     * 查找元素p对应的集合编号
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 && p >= id.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    /**
     * 查询元素p，q是否属于同一个集合
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
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {

        int pID = find(p);
        int qID = find(q);

        if (pID == qID){
            return;
        }

        for (int i = 0; i < id.length; i++){
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
