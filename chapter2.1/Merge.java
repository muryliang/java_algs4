import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge {
    private static Comparable[] aux;
    private static int count = 0;
    public static void sort (Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
        StdOut.println("Merge array access count is " + count);
    }

    public static int  getcnt() {
        return count;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (less(a[mid], a[mid+1]))  {
            count+=2;
            return;
        }
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        count += (hi - lo + 1) * 2;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  {
                a[k] = aux[j++];
                count += 2;
            }
            else  a[k] = aux[i++];
        }
        count += 2;
//        show(a);
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
