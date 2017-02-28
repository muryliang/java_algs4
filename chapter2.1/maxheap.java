import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class maxheap<Item extends Comparable<Item>> implements Iterable<Item> {
    private Item[] arr;
    int N;
    int num;

    public maxheap(int n) {
        N = 0;
        arr = (Item[])new Comparable[n + 1];
        num = n;
    }

    public void insert(Item it) {
        arr[++N] = it;
        swim(N);
    }

    public Item delMax() {
        Item tmp = arr[1];
        exch(1, N--);
        arr[N+1] = null;
        sink(1);
        return tmp;
    }

    public void sort() {
        for (int k = N /2; k >= 1; k--) 
            sink(k);
        while (N > 1) {
            exch(1, N--);
            sink(1);
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator<Item>(arr, num);
    }

    private class ListIterator<Item> implements Iterator<Item>  {
        private Item[] ar;
        int number;
        int i;
        public ListIterator(Item[] array, int max) {
            number = max;
            ar = array;
            i = 0;
        }

        public boolean hasNext() {
            return number > i;
        }

        public void remove() {}

        public Item next() {
            return ar[++i];
        }
    }

    public void print() {
        for (int i = 1; i <= num; i++) 
            StdOut.print(arr[i] + " ");
        StdOut.println();
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
                exch(k/2, k);
                k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k < N) {
            int j = k *2;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch( k , j);
            k = j;
        }
    }

    private boolean less(int i , int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void exch(int i , int j) {
        Item tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        String[] str = StdIn.readAllStrings();
        int len = str.length;
        maxheap<String> pq = new maxheap<String>(len);

        for(int i  = 0; i < len; i++) {
            pq.insert(str[i]);
        }
        StdOut.println();

        pq.sort();
        for (String i: pq)
            StdOut.print(i + " ");
        StdOut.println();
    }
}

