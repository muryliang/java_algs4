//brute force
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class bf {
    public static void main(String[] args) {
        int index = Index(args[0], args[1]);
        StdOut.println("index is " + index);
    }

    public static int Index(String T, String S) {
        int tlen = T.length();
        int slen = S.length();
        int i  = 0;
        while (i < tlen) {
            int j = 0;
            for (; j < slen; j++)
                if (T.charAt(i + j) != S.charAt(j))
                    break;
            if (j == slen)
                return i;
            j = 0;
            i++;
        }
        return -1;
    }
}
