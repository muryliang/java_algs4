import edu.princeton.cs.algs4.StdOut;

public class t18 {
    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a+a, b/2);
        return mystery(a+a, b/2) + a;
    }
    public static void main(String[] args) {
        int result = mystery(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            StdOut.println("result is " + result);
    }
}
