/**
 * className BST
 * description 二分搜索树
 * 传入的E必须具有可比较性所以要继承Comparable
 * @author ln
 * @version 1.0
 * @date 2019/4/14 14:21
 */
public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }


    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
