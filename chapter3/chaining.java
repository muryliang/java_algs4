import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;

public class chaining<Key, Value>  {
    private int n;
    private int M;
    private SequentialSearchST<Key, Value>[] st;

    public chaining() {
        this(997);
    }

    public chaining(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) %M; 
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }
    
    public void put(Key key, Value val) {
        if (st[hash(key)].contains(key))
                n++;
        st[hash(key)].put(key, val);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for(int i = 0; i < M; i++)
            for(Key key : st[i].keys()) 
                queue.enqueue(key);
        return queue;
    }

    public void delete(Key key) {
        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[hash(key)].delete(key);
    }

    public static void main(String[] args) {
        chaining<String, Integer> st = new chaining<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++)
            st.put(StdIn.readString(), i);

        for(String str: st.keys()) 
            StdOut.println(str + " " + st.get(str));
    }
} 
