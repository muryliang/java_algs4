import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quicknorecur {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort (Comparable[] a, int lo, int hi) {
//        JavaStack<Integer> st = new JavaStack<Integer>();
        int count = (int)(2 * Math.log10(hi - lo+1) / Math.log10(2));
        int[] arr = new int[count + 2];
        int i = 0;
        if (hi <= lo + 8)  {
            insertSort(a, lo, hi);
            return;
        }
        arr[i++] = lo;
        arr[i++] = hi;
        while (i != 0) {
            int two = arr[--i];
            int one = arr[--i];
            int mid = partition(a, one, two);
//            StdOut.printf("one %d two %d mid %d\n", one, two, mid);
            if (two - mid < mid -one) {
                if (one < mid-1) {
                    arr[i++] = one;
                    arr[i++] = mid-1;
                }
                if (mid+1 < two) {
                    arr[i++] = mid+1;
                    arr[i++] = two;
                }
            } else {
                if (mid+1 < two) {
                    arr[i++] = mid+1;
                    arr[i++] = two;
                }
                if (one < mid-1) {
                    arr[i++] = one;
                    arr[i++] = mid-1;
                }
            }
        }
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
