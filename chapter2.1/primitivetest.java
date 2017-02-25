import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class primitivetest {
    public static double timeTrial(String alg, int N) {
        double[] a = new double[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        stopwatch time = new stopwatch();
        if ( alg.equals("Insertpri")) Insertpri.sort(a);
        else StdOut.println(" unknown alg");
        return time.elapsedTime();
    }

    public static void main(String[] args) {
        String alg = args[0];
        int times = Integer.parseInt(args[1]);
        for (int N = 250; true; N += N) {
            double time = 0;
            for(int i = 0; i < times; i++)
                time += timeTrial(alg, N);
            time /= times;
            StdOut.printf("cnt %d time is  %5.3f\n", N, time);
        }
    }
}
