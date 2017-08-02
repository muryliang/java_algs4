public class MaxPQ<Key extends Comparable<Key>> {
    
    public MaxPQ() {}

    public MaxPQ(Key v) {}

    Key delMax() {}

    boolean isEmpty() {}

    Key max() {}

    int size() {}

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(a, k/2, k);
            k = k/2;
        }
    }

    public void insert(Key x) {
        pq[++N] = x;
        swim(x);
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(a, 1, N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }

    public void buildUp() {
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }
    }

    public void sort() {
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
}
