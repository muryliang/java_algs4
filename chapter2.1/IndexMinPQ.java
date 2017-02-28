import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IndexMinPQ<Item extends Comparable<Item>> {
    private Item[] pq;
    private int[] index;
    private int N = 0;

    public IndexMinPQ(int n ) {
        pq = (Item[])new Comparable[n+1];
        index = new int[n+1];
    }

    private boolean less(int i, int j) {
        int one = index[i];
        int two = index[j];
        return pq[one].compareTo(pq[two]) < 0;
    }

    private void  exch(int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(k, k/2))
                exch(k/2, k);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if ( j < N && less(j+1, j)) j++;
            if (less(k, j)) break;
            exch(k , j);
            k *= 2;
        }
    }

    public void insert(int idx, Item it) {
        pq[idx] = it;
        index[++N] = idx;
        swim(N);
    }

    public  int delMin() {
        int res = index[1];
        exch(1, N--);
        pq[res] = null;
        index[N+1] = 0;
        sink(1);
        return res;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public boolean contains(int k) {
        return pq[k] != null;
    }
    public void print() {
        for (int i = 0; i < N; i++)
            StdOut.print(index[i] + " ");
        StdOut.println();
    }

    public void change(int k ,Item item) {
        Item cmp = pq[k];
        pq[k] = item;
        if (cmp.compareTo(item) < 0)
            sink(k);
        else
            swim(k);
    }
    public Item min() {
        return pq[index[1]];
    }

}
