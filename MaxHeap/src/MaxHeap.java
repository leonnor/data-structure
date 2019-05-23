/**
 * className MaxHeap
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/5/21 22:29
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int  leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){

        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }
}
