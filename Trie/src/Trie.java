import java.util.TreeMap;

/**
 * className Trie
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/6/29 19:19
 */
public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    /**
     * 字典树的根节点
     */
    private Node root;
    /**
     * 字典树中存储的单词数量
     */
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void add(String word){

        Node cur = root;
        /**
         * 在字典树中按一个个字符查找传入的word参数
         * 若字符c的下一个节点不存在，则在TreeMap中存入（c，new Node()）
         * 然后让当前节点的指针指向c对应的节点
         */
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        /**
         * 对于最后一个节点要注意首先判断它是否已经是一个单词的结尾
         * 避免例如panda和pan的情况
         */
        if (!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词word是否在Trie中
     * @param word
     * @return
     */
    public boolean contains(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if (cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        /**
         * 这里不能直接return true
         * 以panda和pan为例，在查找pan时虽然cur指针确实走到了n这个节点
         * 但是如果pan这个单词并未存储在Trie中（即n节点对应的isWord是false）
         * 这种情况也是属于Trie中没有这个单词
         */
        return cur.isWord;
    }

}
