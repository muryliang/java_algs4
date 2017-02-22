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
        return time.elapsedTime();
    }

    public static void main(String[] args) {
        for (int N = 250; true; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }
}
