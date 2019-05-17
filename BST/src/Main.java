/**
 * className Main
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/4/15 19:23
 */
public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6,8,4,2};
        for (int num : nums){
            bst.add(num);
        }

        bst.preOrder();
        System.out.println();

        bst.preOrderNR();

    }
}
