import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class liner<Key, Value> {
    Key[] keyarr;
    Value[] valarr;
    int M;
    int N;

    public liner() {
        this(997);
    }

    public liner(int m) {
        N = 0;
        M = m;
        keyarr = (Key[])new Object[m];
        valarr = (Value[])new Object[m];
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int h = hash(key);
        for(int i = h ; keyarr[i] != null ; i = (i + 1)%M)
            if (key.equals(keyarr[i]))
                return valarr[i];
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (!contains(key)) N++;
        int h = hash(key);
        int i;
        for (i = h; keyarr[i] != null; i = (i+1)%M)
            if (key.equals(keyarr[i])) {
                valarr[i] = val;
                return;
            }
         keyarr[i] = key;
         valarr[i] = val;
         N++;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int h = hash(key);
        int i;
        for (i = h; keyarr[i] != null; i = (i+1) %M)
            if (keyarr[i].equals(key))
                break;
        i = (i+1)%M;
        for (int k = i; keyarr[k] != null; k = (k+1) %M) {
            Key tmpkey = keyarr[k];
            Value tmpval = valarr[k];
            keyarr[k] = null;
            valarr[k] = null;
            N--;
            put(tmpkey, tmpval);
        }
        N--;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Key key: keyarr) {
            if (key != null)
                queue.enqueue(key);
        }
        return queue;
    }

    public static void main(String[] args) {
        liner<String, Integer> lin = new liner<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++ ) {
            String key = StdIn.readString();
            lin.put(key, i);
        }
        for (String s : lin.keys())
            StdOut.println(s + " " + lin.get(s));
    }
}
            


