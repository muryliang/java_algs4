import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class t2 {
    public static void main(String[] args) {
        int[] num = new int[3];
        num[0] = Integer.parseInt(args[0]);
        num[1] = Integer.parseInt(args[1]);
        num[2] = Integer.parseInt(args[2]);
        if ((num[0] == num[1]) && (num[1] == num[2]))
           StdOut.println("equal");
        else
           StdOut.println("not equal");
    }
}
