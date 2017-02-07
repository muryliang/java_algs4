public class Euclid {
    public static int gcd(int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main(String[] args)
    {
        int result = gcd(5, 10);
        System.out.printf("result is %d\n", result);
    }
}
