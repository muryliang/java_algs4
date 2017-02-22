import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.BinarySearch;
import java.util.Arrays;

public class ThreeSumv3 {
    public static int count(int[] a) {
        int len = a.length;
        int cnt = 0;
        Arrays.sort(a);

        for (int i = 0; i < len; i++) {
            int min = i + 1;
            int max = len - 1;
            while (min < max && a[min] + a[i] <= 0)
                if (a[min] + a[i] + a[max] < 0) min ++;
                else if (a[min] + a[i] + a[max] > 0) max--;
                else { min ++; max --; cnt++;}
        }
        return cnt;
    
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int cnt, MAX = 1000000;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = StdRandom.uniform(-MAX, MAX);
        cnt = ThreeSumv3.count(arr);
        StdOut.printf("the result of %d numbers is %d\n", N, cnt);
    }
}
