import java.util.LinkedList;
import java.util.Queue;
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

    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null){
                q.add(cur.left);
            }
            if (cur.right != null){
                q.add(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    public E minimum(){

        if(size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在节点
     * @param node
     * @return
     */
    private Node minimum(Node node){

        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素
     * @return
     */
    public E maximum(){

        if(size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在节点
     * @param node
     * @return
     */
    private Node maximum(Node node){

        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除二分搜索树中最小的节点，返回最小值
     * @return
     */
    public E removeMin(){

        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中最小节点
     * 返回删除节点后的新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){

        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除二分搜索树中最大的节点，返回最大值
     * @return
     */
    public E removeMax(){

        E ret = maximum();
        root = removeMax(root);
        return ret;
    }


    /**
     * 删除以node为根的二分搜索树中最大节点
     * 返回删除节点后的新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){

        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除节点
     * @param e
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 删除以node为根的二分搜索树中值为e的节点，递归算法
     * 返回删除节点后的新二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e){

        if (node == null){
            return null;
        }

        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
            return node;
        } else {
            /**
             * 删除节点左子树为空
             */
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            /**
             * 删除节点右子树为空
             */
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /**
             * 删除节点左右子树都不为空
             * 思路：找到比待删除节点大的最小节点，即待删除节点右子树最小的节点
             * 用这个节点代替删除节点的位置
             */
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.right = node.left = null;
            return successor;
        }
    }
}
