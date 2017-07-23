import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

public class Permutation {

    public static void main(String[] args) {
        /**
         * insert k+1 members ,and randomly delete one
         */
        int N = Integer.parseInt(args[0]);
        int count = 0;
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (count!= N+1)  {
                rq.enqueue(str);
                count++;
            }
            if (count == N+1) { 
                rq.dequeue();
                count--;
            }
        }
        int num = 0;
        for (String tmp: rq) {
            StdOut.println(tmp);
            if (num ++ == N)
                break;
        }
    }
}
