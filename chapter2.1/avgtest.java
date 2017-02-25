import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;

public class avgtest {
    public static double timeTrial(String alg, int N) {
        Double[] a = new Double[N];

        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform();
        stopwatch time = new stopwatch();
        if ( alg.equals("Insertion")) Insert.sort(a);
        else if ( alg.equals("Selection")) Selection.sort(a);
        else if ( alg.equals("Shell")) Shell.sort(a);
        else StdOut.println(" unknown alg");
        return time.elapsedTime();
    }   

    public static void main(String[] args) {
        String alg = args[0];
        int times = 0;
        int length = Integer.parseInt(args[1]);

        StdDraw.setXscale(0, 50);
        StdDraw.setYscale(-1, 4);
        StdDraw.setPenRadius(0.005);
        double time = 0, curtime = 0;
        for (; ; ) { 
            curtime = timeTrial(alg, length);
            time += curtime;
            times++;
            StdOut.printf("cnt %d time is  %5.3f, curtime is %5.3f\n", length,  time / times, curtime );
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(times, time / times);
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.point(times, curtime);
        }   
    }   
}
