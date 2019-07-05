import java.util.ArrayList;

/**
 * className RBTree
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/7/5 10:04
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 判断节点node的颜色
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        if (node == null){
            return BLACK;
        }
        return node.color;
    }

    /**
     * 颜色翻转
     * @param node
     */
    private void flipColors(Node node){

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rightRotate(Node node){

        Node x = node.left;

        /**
         * 向右旋转过程
         */
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 左旋转操作
     * @param node
     * @return
     */
    private Node leftRotate(Node node){

        Node x = node.right;

        /**
         * 向左旋转过程
         */
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    public void add(K key, V value){
        root = add(root, key, value);
        root.color = BLACK;
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

        if (isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node  = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }


}

