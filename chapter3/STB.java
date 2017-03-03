import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class STB <Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private int size(Node node) {
        if (node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        return node.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0 ) node.left = put(node.left, key, val);
        else if (cmp > 0 ) node.right = put(node.right, key, val);
        else node.val = val;
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int rank(Key key) {
        if (get(key) == null)
            return -1;
        return rank(root, key);
    }
    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return size(node.left);
        if (cmp < 0) return rank(node.left, key);
        return 1 + size(node.left) + rank(node.right , key);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return x.left;
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return x.right;
    }
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t == null) return x;
        else return t;
    }

    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t == null) return x;
            return t;
        }
        if (cmp == 0) return x;
        return ceiling(x.right, key);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key); 
    }

    public int allheight() {
        return allheight(root);
    }

    private int  allheight(Node x) {
        if (x == null) return 0;
        return max(allheight(x.left), allheight(x.right)) + 1;
    }

    public int max(int x, int y) {
        return x > y ? x: y;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        if (cmp > 0) x.right = delete(x.right, key);
        if (cmp == 0) {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;
            Node min = min(x.right);
            min.right = deleteMin(x.right);
            min.left = x.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> traverse() {
        Queue<Key> queue = new Queue<Key>();
        traverse(queue, root, min(), max());
        return queue;
    }

    private void traverse(Queue<Key> queue, Node root, Key lo, Key hi) {
        if (root == null) return;
        int cmplo = lo.compareTo(root.key);
        int cmphi = hi.compareTo(root.key);
        if (cmplo < 0) traverse(queue, root.left, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(root.key);
        if (cmphi > 0) traverse(queue, root.right, lo, hi);
    }

    public static void main(String[] args) {
        String[] str = StdIn.readAllStrings();
        STB<String, Integer> bst = new STB<String, Integer>();
        for (int i = 0; i < str.length; i++) {
            bst.put(str[i], i);
            StdOut.println(str[i] + " " + i );
        }

        StdOut.println("begin print get method sequence");
        for (int i = 0; i < str.length; i++) {
            int k = bst.get(str[i]);
            StdOut.println(k + " value " + str[i]);
        }
            
        StdOut.println("begin print traverse");
        for (String x: bst.traverse()) {
            StdOut.print(x + " ");
        }
    }
}
