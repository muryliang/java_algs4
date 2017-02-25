import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class stopwatch {
    private final long start;
    public stopwatch () {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public static void main(String[] args) {
        stopwatch prev = new stopwatch();

        StdOut.println("now begin to do something");
        for(int i = 0; i < 10000; i++)
            for (int j = 0; j < 10000; j++)
                ;
        StdOut.printf("the result time is %f\n", prev.elapsedTime());
    }
}

