import edu.princeton.cs.algs4.StdOut;

public class t7 {
    public static void main(String[] args) {
        double t = 9.0;
        int sum = 0, sum2 = 0;
        while (Math.abs(t - 9.0/t) > .001)
            t = (9.0/t + t) / 2.0;
        StdOut.printf("%.5f\n", t);

        for (int i = 1; i < 1000; i++)
            for(int j = 0; j < i; j++)
                sum++;
        StdOut.println(sum);

        for (int i = 1; i < 1000; i *= 2)
            for(int j = 0; j < i; j++)
                sum2++;
        StdOut.println(sum2);
    }
}
