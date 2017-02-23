import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class doubletest {
    public static double timeTrial(int N) {
        int Max = 1000000;
        int[] a = new int[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-Max, Max);
        stopwatch time = new stopwatch();
        int cnt = ThreeSumv3.count(a);
        StdOut.println("cnt " + cnt);
        return time.elapsedTime();
    }

    public static void main(String[] args) {
        int times = Integer.parseInt(args[0]);
        for (int N = 250; true; N += N) {
            double time = 0;
            for(int i = 0; i < times; i++)
                time += timeTrial(N);
            time /= times;
            StdOut.printf("%7d %5.3f\n", N, time);
        }
    }
}
