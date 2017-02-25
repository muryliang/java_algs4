import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shell {
    public static void sort (Comparable[] a) {
        int  N = a.length;
        int cal = 1, k = 0, count = 0;
        while (cal < N/3)  {
            cal = 3*cal + 1; // so we have h = 1/2 * (3^k - 1)
            count++; 
        }
        int[] inc = new int[count];
        for(int i = 0 ; i < count; i++)
            inc[i] = (k = k * 3 + 1);
//        for(int i: inc)
 //           StdOut.print(i+ " ");
  //      StdOut.println();


        while (--count >= 0) {
            int h = inc[count];
            for (int i = h; i < N; i++) {
                Comparable t = a[i];
                int j;
                for (j = i - h; j >= 0 && less(a[i], a[j]); j-=h)
                    ;
                for (int m = i - h; m > j; m -= h)
                    a[m + h] = a[m];
                a[j + h] = t;
//                show(a);
            }
   //         StdOut.println();
//            h /= 3;
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
