import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;

public class norecursiveSTB<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node { 
        Key key;
        Value val;
        Node left, right;
    
        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        Node z = new Node(key, val);
        if (root == null)  { 
            root = z;
            return;
        }

        Node prev = null, x = root;
        while (x != null) {
            prev = x;
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else {x.val = val; return;}
        }

        int cmp = key.compareTo(prev.key);
        if (cmp < 0) prev.left = z;
        else        prev.right = z;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public Iterable<Key> keys() {
        Stack<Node> st = new Stack<Node>();
        Queue<Key> qu = new Queue<Key>();
        Node x = root;
        while (x != null) {
            if (x != null) {
                st.push(x);
                x = x.left;
            } else {
                x = st.pop();
                qu.enqueue(x.key);
                x = x.right;
            }
        }
        return qu;
    }

}
