import edu.princeton.cs.algs4.StdOut;

public class t9 {
    public static void main (String[] args) {
        String s = "";
        for (int n = Integer.parseInt(args[0]); n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        StdOut.println(s);
    }
}
