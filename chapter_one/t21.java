import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class t21 {
    public static void main(String[] args) {
            String var;
            double a, b;
        while (!StdIn.isEmpty()) {
            var = StdIn.readString();
            a = Double.parseDouble(StdIn.readString());
            b = Double.parseDouble(StdIn.readString());
            StdOut.printf("%s %.3f %.3f ==> %.3f\n", var, a, b, a/b);
        }
    }
}


