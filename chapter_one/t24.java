import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class t24 {
    public static int Euclid(int a, int b) {
        StdOut.println("compare: " + a + " " + b);
        if (b == 0) return a;
        return Euclid(b, a%b);
    }
    public static void main(String[] args) {
        int divided, dividor;

        divided = Integer.parseInt(args[0]);
        dividor = Integer.parseInt(args[1]);
        StdOut.printf("common divisor of %d and %d is %d\n", divided, dividor, Euclid(divided, dividor));
    }
}
