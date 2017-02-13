import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdRandom;

public class t3 {
    public static int count;
    public static Interval1D[] pt; 
    public static Interval2D[] pt2d;

    public static void main(String[] args) {
        double x1, y1, x2, y2; 
        count = Integer.parseInt(args[0]);
        pt = new Interval1D[count * 2]; 
        pt2d = new Interval2D[count];

        for (int i = 0; i < count; i++) {
            x1 = StdRandom.uniform();
            y1 = StdRandom.uniform();
            x2 = StdRandom.uniform();
            y2 = StdRandom.uniform();
            pt[i] = new Interval1D(x1 < y1 ? x1 : y1, x1 < y1 ? y1 : x1);
            pt[i + count] = new Interval1D(x2 < y2 ? x2 : y2, x2 < y2 ? y2 : x2);
            pt2d[i] = new Interval2D(pt[i], pt[i + count]);
//            StdOut.println(pt[i]);

        }   

        for (int i = 0; i < count ; i++ )  {
            pt2d[i].draw();
            for (int j = i + 1; j < count; j++)
                if (pt2d[i].intersects(pt2d[j])) 
                    StdOut.println(pt2d[i] + " " + pt2d[j]);
        }   
    }   
}                                   
