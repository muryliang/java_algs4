import edu.princeton.cs.algs4.StdOut;

public class t27 {
    public static double binomial(int N, int k, double p) {
        if ((N == 0) || (k < 0)) return 1.0;
        return (1.0 - p) * binomial(N-1, k, p) + p * binomial(N-1, k-1, p);
    }

    public static void main(String[] args) {
        double res = binomial(Integer.parseInt(args[0]), Integer.parseInt(args[1]), 0.1);
        StdOut.println("result is " + res);
    }
}
