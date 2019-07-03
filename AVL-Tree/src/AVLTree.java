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

    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){

        if (node == null){
            size++;
            return new Node(key, value);
        }

        /**
         * 更新height
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        return node;
    }
}
