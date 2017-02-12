import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class t15 {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] arr = in.readAllInts();
        int number = StdIn.readInt();
        int count[] = new int[number];

        for (int i = 0; i < arr.length ; i++) {
            if (arr[i] < number)
                count[arr[i]]++;
        }

        for (int i = 0; i < number; i++)
            StdOut.printf("%d ", count[i]);
        StdOut.println("");
    }
}

