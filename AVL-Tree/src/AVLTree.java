import java.util.ArrayList;

/**
 * className AVLTree
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/3 11:32
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 计算平衡因子
     */
    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    /**
     * 寻找二分搜索树的最小元素
     * @return
     */
    public K minimum(){

        if(size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        return minimum(root).key;
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
     * 删除二分搜索树中最小的节点，返回最小值
     * @return
     */
    public K removeMin(){

        K ret = minimum();
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
     * 判断该二叉树是否是二分搜索树
     * @return
     */
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i++){
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){

        if (node == null){
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断是否是平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){

        if (node == null){
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点y进行右旋转操作，返回旋转后的根节点
     * @param y
     * @return
     */
    private Node rightRotate(Node y){

        Node x = y.left;
        Node T3 = x.right;

        /**
         * 向右旋转过程
         */
        x.right = y;
        y.left = T3;

        /**
         * 更新height
         */
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * 左旋转操作
     * @param y
     * @return
     */
    private Node leftRotate(Node y){

        Node x = y.left;
        Node T2 = x.right;

        /**
         * 向左旋转过程
         */
        x.right = y;
        y.right = T2;

        /**
         * 更新height
         */
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){

        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }
        else if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }
        else {
            node.value = value;
        }

        /**
         * 更新height
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        /**
         * 平衡维护
         */
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    /**
     * 从二分搜索树中删除节点
     * @param key
     */
    public void remove(K key){
        root = remove(root, key);
    }

    private Node remove(Node node, K key){

        if (node == null){
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else {
            //待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode =  rightNode;
            }

            //待删除节点右子树为空的情况
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }

            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点，用这个节点顶替待删除节点的位置
            else {
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null){
            return null;
        }

        /**
         * 更新height
         */
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(retNode);

        /**
         * 平衡维护
         */
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }
}
