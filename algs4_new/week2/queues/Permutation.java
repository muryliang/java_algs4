import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            rq.enqueue(str);
        }

        int num = 0;
        for (String tmp: rq) {
            if (num++ >= N)
                break;
            StdOut.println(tmp);
        }
    }
}
