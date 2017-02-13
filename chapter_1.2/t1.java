import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;

public class t1 {
    public static int count;
    public static Point2D[] pt;
    public static Point2D dis1, dis2;

    public static void main(String[] args) {
        double dis = 10;
        count = Integer.parseInt(args[0]);
        pt = new Point2D[count];

        for (int i = 0; i < count; i++) {
            pt[i] = new Point2D(StdRandom.uniform(), StdRandom.uniform());
            StdOut.println(pt[i]);

        }

        for (int i = 0; i < count - 1; i++ ) 
            for (int j = i + 1; j < count; j++)
                if (pt[i].distanceTo(pt[j]) < dis) {
                        dis = pt[i].distanceTo(pt[j]);
                        dis1 = pt[i];
                        dis2 = pt[j];
                }

        StdOut.printf("%s : %s ==> %f\n", dis1.toString(), dis2.toString(), dis);
    }
}
