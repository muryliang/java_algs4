import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class t16 {
    public static String ex(int n) {
        if (n <= 0) return "";
        return ex(n-3) + n + ex(n-2) + n;
    }

    public static void main(String[] args) {
        String result = ex(Integer.parseInt(args[0]));
        StdOut.println("result is " + result);
    }
}
