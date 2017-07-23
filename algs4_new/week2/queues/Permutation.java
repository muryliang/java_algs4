import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            count++;
            String str = StdIn.readString();
            if (rq.size() != N) {
                rq.enqueue(str);
            } else {
                int index = StdRandom.uniform(count);
                if (index < N) {
                    rq.dequeue();
                    rq.enqueue(str);
                }
            }
        }

        int num = 0;
        for (String tmp: rq) {
            if (num++ >= N)
                break;
            StdOut.println(tmp);
        }
    }
}
