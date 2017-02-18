import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Binary {
    public static void main(String[] args) {
        JavaStack<Integer> stack = new JavaStack<Integer>();
        int N = StdIn.readInt();

        while (N > 0) {
            stack.push(N % 2);
            N /= 2;
        }

        for (int i : stack)
            StdOut.print(i);
        StdOut.println();
    }
}
