/**
 * className UF
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/1 14:56
 */
public interface UF {

    int getSize();
    boolean isConnect(int p, int q);
    void unionElements(int p, int q);
}
