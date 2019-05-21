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


}
