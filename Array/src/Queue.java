/**
 * className Queue
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/4 21:03
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
