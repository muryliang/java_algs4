import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class STBinary<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    
    public STBinary(int capacity) {
        keys = (Key[])new Object[capacity];
        vals = (Value[])new Object[capacity];
        N = capacity;
    }

    public Value get(Key key) {
        int idx = rank(key);
        if (idx < N && key.compareTo(keys[idx]) == 0)
            return vals[idx];
        return null;
    }

    public void set(Key key, Value val) {
        int idx = rank(key);
        if (idx < N && key.compareTo(keys[idx]) == 0)
            vals[idx] = val;
        else {
            for (int j = N; j > idx; j--) {
                keys[j] =  keys[j-1];
                vals[j] = vals[j-1];
            }
            N++;
        }
    }

    public void delete(Key key) {
        int idx = rank(key);
        if (idx < N && key.compareTo(keys[idx]) == 0) {
            for (int j = idx; j < N; j++){
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            N--;
        }
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) /2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else
                return mid;
        }
        return lo;
    }

    public Key min() { return keys[0];}
    public Key max() { return keys[N-1];}
    public Key select(int k) {
        return keys[k];
    }
    public Key ceiling(Key key) {
        int idx = rank(key);
        return keys[idx];
    }
    public Key floor(Key key) {
        int idx = rank(key);
        if (key.compareTo(keys[idx]) == 0)
            return keys[idx];
        else {
            if (idx == 0)
                return null;
            else 
                return keys[idx-1];
        }
    }

    public boolean contains(Key k) {
        int idx = rank(k);
        return (idx < N && k.compareTo(keys[idx]) == 0);
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++)
            q.enqueue(keys[i]);
        if (contains(hi))
            q.enqueue(keys[rank(hi)]);
        return q;
    }
}

