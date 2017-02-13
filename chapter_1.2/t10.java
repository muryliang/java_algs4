import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class t10 {
    private int max;
    private int count;
    public t10(int num) {
        max = num;
        count = 10;
    }   

    public void inc() {
        if (count < max) 
            count++;
    }   

    public void dec() {
        if (count >0) 
            count--;
    }   

    public int get() {
        return count;
    }   

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        t10 tt = new t10(20);

        StdDraw.setPenRadius(0.01);
        for (int i = 0; i < N; i++) {
            if (StdRandom.uniform() < 0.5)
                tt.inc();
            else
                tt.dec();
            StdOut.println(tt.get());
            StdDraw.point(1.0/N * i, tt.get() / 20.0);
         }   
    }   
}
