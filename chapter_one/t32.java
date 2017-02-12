import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;

public class t32 {
    public static int N;
    public static double low;
    public static double high;
    public static double[] x;
    public static double[] y;
    public static double width;
    public static int[] count;

    public static void fill_arrayx() {
        double dis = (high-low) / N;
        width = dis / 2 / 2 / 100;

        for (int i = 0; i < N; i++) {
            x[i] = dis / 2 + i * dis;
            x[i] /= 100;
        }
    }   

    public static void draw_pic() {
        StdOut.printf("width %f\n", width);
        for(int i = 0; i < N; i++)  {
            y[i] = count[i] / 2.0;
            y[i] /= 10;
            StdOut.printf("x: %f  y: %f\n", x[i], y[i]);
            StdDraw.filledRectangle(x[i], y[i], width, y[i]);
        }   
    }   
            
    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        low = Double.parseDouble(args[1]);
        high = Double.parseDouble(args[2]);

        x = new double[N];
        y = new double[N];
        count = new int[N];
        fill_arrayx();
        while (!StdIn.isEmpty()) {
            double input = StdIn.readDouble();
            int index = (int)((input - low) / ((high - low) / N));
            count[index] ++; 
            StdOut.printf("%f belongs  to slot %d\n", input, index);
        }   
        draw_pic();
    }   
}
