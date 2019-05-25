/**
 * className PriorityQueue
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/5/24 14:47
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extraMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
