import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class Selection {
    public static void sort (Comparable[] a) {
        int max;
        for (int i = 0; i < a.length-1; i++) {
            max = i;
            for (int j = i + 1; j < a.length; j++)
                if (less(a[j], a[max]))
                        max = j;
            exch(a, i, max);
           // show(a);
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

    private static void draw(Comparable[] a, int[] high) {
        int N = a.length;
        double sum = 0.5;
        double[] xl = new double[N];
        double[] y = new double[N];

        double width = 0.25 / N;
        for (int i = 0; i < N; i++) {
            xl[i] = 0.5 / N + i* 1.0 / N ;
            y[i] = sum / N * high[i] / 2;
        }

        StdDraw.setPenColor(StdDraw.BLUE);
        for (int i = 0; i < N; i++) {
           StdDraw.filledRectangle(xl[i], y[i]+0.25, width, y[i]);
        }
        StdDraw.setPenColor();
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);
        show(a);
        int[] high = new int[a.length];
        for (int i = 0; i < a.length; i++)
            high[i] = i+1;
//        draw(a, high);
    }
}
         
