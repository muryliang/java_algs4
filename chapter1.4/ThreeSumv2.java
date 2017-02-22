import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.BinarySearch;

public class ThreeSumv2 {
    public static int count(int[] a) {
        long len = a.length;
        int cnt = 0;

        for(int i = 0; i < len; i++) 
            for (int j = i+1; j < len; j++) 
                if (BinarySearch.indexOf(a, -1 * (a[i] + a[j])) != -1)
                        cnt++;
        return cnt;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int cnt, MAX = 1000000;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(-MAX, MAX);
        cnt = ThreeSumv2.count(arr);
        StdOut.printf("the result of %d numbers is %d\n", N, cnt);
    }
}
