/**
 * className Map
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/5/18 15:51
 */
public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
