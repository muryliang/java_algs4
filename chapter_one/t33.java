import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class t33 {

    public static double dot(double[] x, double[] y) {
        double res = 0;
        
        if (x.length != y.length) return -1;
        for (int i = 0; i < x.length; i++)
            for(int j = 0; j < x.length; j++)
                res += x[i] * y[i];
        return res;
    }

    public static double[][] mult(double[][] a, double[][] b) {
        double[][] res = new double[a.length][b[0].length];

        double[][] transb = transpose(b);
        for (int i = 0; i < a.length; i++) 
            for(int j = 0; j < b[0].length; j++)
                res[i][j] = dot(a[i], transb[j]);
        return res;
    }

    public static double[][] transpose(double[][] a) {
        double[][] res = new double[a[0].length][a.length];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                res[j][i] = a[i][j];
        return res;
    }

    public static double[] mult(double[][] a, double[] x) {
        double[] y = new double[x.length];

        for (int i = 0; i < x.length; i++)
            y[i] = dot(a[i], x);
        return y;
    }

    public static double[] mult(double[] y, double[][] a) {
        double[] x = new double[y.length];

        for (int i = 0; i < y.length; i++)
            x[i] = dot(y, a[i]);
        return x;
    }

    public static void show_matrix(double[][] x) {
        for(int i = 0; i < x.length; i++) {
            for(int j = 0; j < x[0].length; j++)
                StdOut.printf("%6.2d ", x[i][j]);
            StdOut.println();
        }
    }
}

