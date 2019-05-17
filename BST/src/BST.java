import java.util.Stack;

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

    /** 向二分搜索树中添加新的元素e*/
    public void add(E e){

        root = add(root, e);
    }

    /** 向以node为根的二分搜索树中插入元素E， 递归算法
     *  返回插入新结点后二分搜索树的根
     * */
    private Node add(Node node, E e){

        if (node == null){
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0){
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    /** 看二分搜索树中是否包含元素e*/
    public boolean contains(E e){
        return contains(root, e);
    }

    /** 看以node为根的二分搜索树中是否包含元素e，递归算法*/
    private boolean contains(Node node, E e){

        if (node == null){
            return false;
        }

        if (e.compareTo(node.e) == 0){
            return true;
        } else if (e.compareTo(node.e) < 0){
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /** 二分搜索树的前序遍历*/
    public void preOrder(){
        preOrder(root);
    }

    /** 前序遍历以node为根的二分搜索树，递归算法*/
    private void preOrder(Node node){
        //递归终止条件
        if (node == null){
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树非递归前序遍历
     */
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){

        if (node == null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        preOrder(root);
    }

    private void postOrder(Node node) {

        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }
}
