/**
 * className Stack
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/4 20:41
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
