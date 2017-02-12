import edu.princeton.cs.algs4.StdOut;

public class t20 {
    public static double mylog(int n) {
        if (n == 1) return 0;
        return mylog(n-1) + Math.log((double)n);
    }

    public static void main(String[] args) {
        double result = mylog(Integer.parseInt(args[0]));
        StdOut.println("result is " + result);
    }
}

