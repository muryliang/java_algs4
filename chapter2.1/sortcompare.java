import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickX;

public class sortcompare {
    public static double time(String alg, Double[] a) {
        stopwatch prev = new stopwatch();
        if (alg.equals("Insertion")) Insert.sort(a);
        else if (alg.equals("Selection")) Selection.sort(a);
        else if (alg.equals("Shell")) Shell.sort(a);
        else if (alg.equals("Merge")) Merge.sort(a);
        else if (alg.equals("MergeInsert")) MergeInsert.sort(a);
        else if (alg.equals("Mergencopy")) Mergencopy.sort(a);
        else if (alg.equals("MergeBu")) MergeBu.sort(a);
        else if (alg.equals("Quick")) Quick.sort(a);
        else if (alg.equals("QuickX")) QuickX.sort(a);
        else if (alg.equals("Quickdup")) Quickdup.sort(a);
        else StdOut.println("do not know alg");
        return prev.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N ; i++)
                a[i] = StdRandom.uniform();
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.println("time is " + t1 + " " + t2);
        StdOut.printf("For %d random Doubles\n  %s is", N, alg1);
        StdOut.printf(" %.1f times faster than %s\n", t2/t1,  alg2);
        StdOut.printf(" 6nlogn is %f\n", 6 * Math.log10(N)/ Math.log10(2) * N);
    }
}

