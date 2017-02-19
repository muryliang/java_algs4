import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Josephus2 {
    private static int N;
    private static int M;
    public static void main(String[] args) {
        N = Integer.parseInt(args[0]); 
        M = Integer.parseInt(args[1]); 
        int[] array = new int[N];

        for(int i = 0; i < N; i++)
            array[i] = i;

//        for(int i:array)
 //           StdOut.print(i + " ");
  //      StdOut.println();
        do_Josephus2_2();
    }

    public static void do_Josephus2_2() {
        int s = 0;
        for (int i = 2; i <= N; i++)
            s = (s + M) % i;
        StdOut.println("pos is " + s);
    }
    public static void do_Josephus2() {
        int pos;
        pos = Josephus_rec(N, M);
        StdOut.println("the pos last is " + pos);
    }

    private static int Josephus_rec(int n, int m){
        if (n ==1 )
            return 0;
        return (Josephus_rec(n-1, m) + m ) % n;
    }
}
