import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quickfast {

    public static void sort(Comparable[] a) {
//        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort (Comparable[] a, int lo, int hi) {
		int n = hi - lo + 1;
        if (n <= 8)  {
            insertSort(a, lo, hi);
            return;
        } else if (n <= 40) {
			int tmp = median( a, lo, lo + n/2, hi);
	//        StdOut.println("lo  hi mid tmp "+ a[lo] + " " +   a[hi] + " " + a[lo + (hi - lo) /2] + " " + a[tmp]);
			exch(a, tmp, lo);
		} else  {
            int eps = n/8;
            int mid = lo + n/2;
            int m1 = median(a, lo, lo + eps, lo + eps + eps);
            int m2 = median(a, mid - eps, mid, mid + eps);
            int m3 = median(a, hi - eps - eps, hi - eps, hi); 
            int ninther = median(a, m1, m2, m3);
            exch(a, ninther, lo);
        }   
		Comparable v = a[lo];
        int p = lo , i = lo , q = hi+1, j = hi+1;
		while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i == j && eq(a[i], v))
                exch(a, i, ++p);
            if (i >= j)
                break;
            exch(a, i, j);
            if (eq(a[i], v)) exch(a, i, ++p);
            if (eq(a[j], v)) exch(a, j, --q);
		}
        i = j + 1;
        for (int k = lo; k <= p; k++)
            exch(a, k, j--);
        for (int k = hi; k >= q; k--)
            exch(a, k, i++);
        sort(a, lo, j);
        sort(a, i, hi);
    }

	private static int  median(Comparable[] a, int lo, int mid, int hi) {
		return less(a[lo], a[mid]) ? (less(a[mid], a[hi]) ? mid : less(a[lo], a[hi])?hi : lo) : (less(a[hi], a[mid]) ? mid :
				(less(a[lo], a[hi]) ? lo : hi));
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

    private static boolean eq (Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
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
