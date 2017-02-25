import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Insertpri {
    public static void sort (double[] a) {
        for(int i = 1; i < a.length; i++) {
//            while(i > 0 && less(a[i], a[i-1])) {
 //               exch(a,i, i-1);
  //              i--;
   //         }
            int j  = i - 1;
            while (j >= 0 && less(a[i], a[j]))
                j -= 1;
            double t = a[i];
            for (int k = i - 1; k > j; k--)
                a[k + 1] = a[k];
            a[j + 1] = t;
        }
    }

    private static boolean less (double a, double b) {
        return a < b;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i]; 
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++ )
            if (less(a[i], a[i-1])) return false;

        return true;
    }

    private static void show(double[] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }

    public static void main(String[] args) {
        double[] a = StdIn.readAllDoubles();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
