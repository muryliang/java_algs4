import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Arrays;

public class schedule {
	public static void main(String[] args) {
		MinPQ<String> pq = new MinPQ<String>();
		In in = new In(args[0]);
		String[] arr = in.readAllStrings();
		for ( int i = 0; i < arr.length; i++) {
			pq.insert(arr[i]);
		}
		while (!pq.isEmpty())
			StdOut.print(pq.delMin() + " ");
		StdOut.println();
	}
}
