import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    private static int M, N;
    private static int[] array;

    public static void main(String[] args) {
        N = Integer.parseInt(args[0]);
        M = Integer.parseInt(args[1]);
        array = new int[N];

        for (int i = 0; i < N; i++)
            array[i] = i;
//        for (int i: array)
 //           StdOut.print(i + " ");
        StdOut.println();

        do_Josephus();
    }

    public static void do_Josephus() {
        int count = N;
        int iter = N-1;
        while(count != 0) {
           int subiter = M;
           while(subiter-- != 0){
               iter = (iter + 1) % N;
           //    StdOut.println("now iter is " + iter);
               while(array[iter] == -1) 
                   iter = (iter + 1) % N;
           }
           if (count == 1)
               StdOut.print(array[iter] + " ");
           array[iter] = -1;
           count--;
        }
        StdOut.println();
    }
}
