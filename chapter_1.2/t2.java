import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdRandom;

public class t2 {
    public static int count;
    public static Interval1D[] pt;

    public static void main(String[] args) {
        double dis = 10;
        double x, y;
        count = Integer.parseInt(args[0]);
        pt = new Interval1D[count];

        for (int i = 0; i < count; i++) {
            x = StdRandom.uniform();
            y = StdRandom.uniform();
            pt[i] = new Interval1D(x < y ? x : y, x < y ? y : x);
//            StdOut.println(pt[i]);

        }

        for (int i = 0; i < count - 1; i++ ) 
            for (int j = i + 1; j < count; j++)
                if (pt[i].intersects(pt[j])) 
                    StdOut.println(pt[i] + " " + pt[j]);

    }
}
