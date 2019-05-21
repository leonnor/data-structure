/**
 * className BSTMap
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/5/20 16:09
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    /**
     * 辅助函数
     * 返回以node为根的二分搜索树中，key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key){

        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) == 0){
            return node;
        } else if (key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){

        if (node == null){
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除以node为根的二分搜索树的最小节点
     * 返回删除节点后新的树的根
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

    @Override
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null){
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key){
        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        } else {
            /**
             * 删除节点左子树为空
             */
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            /**
             * 删除节点右子树为空
             */
            if (node.right == null) {
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

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {

        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
