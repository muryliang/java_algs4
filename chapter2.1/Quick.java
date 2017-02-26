import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort (Comparable[] a, int lo, int hi) {
        if (hi <= lo + 15)  {
            insertSort(a, lo, hi);
            return;
        }
        int mid = partition(a, lo, hi);
        sort(a, lo, mid-1);
        sort(a, mid + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i  = lo, j = hi + 1;
        Comparable v  = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
//        StdOut.printf("lo %d hi %d mid %d\n", lo, hi, j);
        return j;
    }

    private static void insertSort (Comparable[] a, int lo, int hi) {
        int length = hi - lo + 1;
        for(int i = lo; i <= hi; i++) {
            int j  = i - 1;
            while (j >= lo && less(a[i], a[j]))
                j -= 1;
            Comparable t = a[i];
            for (int k = i - 1; k > j; k--)
                a[k + 1] = a[k];
            a[j + 1] = t;
        }
    }
    private static boolean less (Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; 
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++ )
            if (less(a[i], a[i-1])) return false;

        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
