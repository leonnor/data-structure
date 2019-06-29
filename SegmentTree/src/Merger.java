/**
 * className Merger
 * description 融合器
 *
 * @author ln
 * @version 1.0
 * @date 2019/6/28 13:59
 */
public interface Merger<E> {

    E merge(E a, E b);
}
