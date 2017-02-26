import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.QuickX;

public class doubletest {
    public static double timeTrial(String alg, int N, String mode) {
        Double[] a = new Double[N];

        if (mode.equals("order"))
            for (int i = 0; i  < N; i++)
                a[i] = i * 1.0;
        else if (mode.equals("reverse"))
            for (int i = 0; i  < N; i++)
                a[i] = N - i * 1.0;
        else if (mode.equals("same"))
            for (int i = 0; i  < N; i++)
                a[i] = 10.0;
        else if (mode.equals("two"))
            for (int i = 0; i  < N; i++)
                a[i] = StdRandom.bernoulli(0.5) ? 1.0 : 2.0;
        else if (mode.equals("none"))
            for (int i = 0; i < N; i++)
                a[i] = StdRandom.uniform();
        else 
            throw new IllegalArgumentException("no correct order mode specified");
        stopwatch time = new stopwatch();
        if ( alg.equals("Insertion")) Insert.sort(a);
        else if ( alg.equals("Selection")) Selection.sort(a);
        else if ( alg.equals("Shell")) Shell.sort(a);
        else if ( alg.equals("Merge")) Merge.sort(a);
        else if ( alg.equals("MergeInsert")) MergeInsert.sort(a);
        else if ( alg.equals("Mergencopy")) Mergencopy.sort(a);
        else if ( alg.equals("MergeBu")) MergeBu.sort(a);
        else if ( alg.equals("Quick")) Quick.sort(a);
        else if ( alg.equals("QuickX")) QuickX.sort(a);
        else StdOut.println(" unknown alg");
        return time.elapsedTime();
    }

/*
 * main have arguments:
 *  algirthms: Selection Insertion Shell
 *  do times : every order number's running times
 *  array number mode: none(random from 0 to 1 doubles)
 *                     order, same, two(only two members), reverse
 */

    public static void main(String[] args) {
        String alg = args[0];
        double prev = 0;
        double bei = 0;
        if ( alg.equals("Insertion")) bei = 4;
        else if ( alg.equals("Selection")) bei = 4;
        else if ( alg.equals("Shell")) bei = 2 * Math.sqrt(Math.sqrt(2));
        else if ( alg.equals("Merge")) bei = 2;
        int times = Integer.parseInt(args[1]);
        String corner = "none";
        if (args.length == 3)
            corner = args[2];

//        StdDraw.setXscale(5, 30);
 //       StdDraw.setYscale(-1, 20);
  //      StdDraw.setPenRadius(0.01);
        for (int N = 128; true; N += N) {
            double time = 0;
            for(int i = 0; i < times; i++)
                time += timeTrial(alg, N, corner);
            time /= times;
            StdOut.printf("cnt %d time is  %5.3f, predicted is %5.3f\n", N, time, prev == 0 ? 0 : prev * bei);
//            StdDraw.point(Math.log10(N) / Math.log10(2), time);
            prev = time;
        }
    }
}
