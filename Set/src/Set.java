/**
 * className Set
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/5/18 13:02
 */
public interface Set<E> {

    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
