import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;
import java.lang.Math;

public class t31 {
    private static double basex = 0;
    private static double basey = 0;
    private static double R = 0;
    private static int N = 0;
    private static double ratio = 0;
    private static double[] x;
    private static double[] y;

    public static void fill_point(double[] x, double[] y) {
        double unit = Math.PI / N * 2;
        for (int i = 0; i < N; i++) {
            x[i] = basex + Math.cos(i * unit) * R ; 
            y[i] = basey + Math.sin(i * unit) * R ; 
        }   
    }   

    public static void main(String[] args) {
        basex = Double.parseDouble(args[0]);
        basey = Double.parseDouble(args[1]);
        R = Double.parseDouble(args[2]);
        N = Integer.parseInt(args[3]);
        ratio = Double.parseDouble(args[4]);

        x = new double[N];
        y = new double[N];
        fill_point(x, y); 

        StdDraw.circle(basex, basey, R); 
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(StdDraw.GREEN); 
        for (int i = 0; i < N; i++)
            StdDraw.point(x[i],y[i]);
        
        StdDraw.setPenRadius();
        StdDraw.setPenColor();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if ( StdRandom.uniform() < ratio)
                    StdDraw.line(x[i],y[i],x[j],y[j]);
    }   
}

