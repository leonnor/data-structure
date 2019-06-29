/**
 * className Main
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/6/28 14:06
 */
public class Main {

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        //System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
    }
}
