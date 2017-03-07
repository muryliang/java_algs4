import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class kmp {
    public static void main(String[] args) {
        String T = args[0];
        String S = args[1];
        int[] next = new int[S.length()];

        getnext(S, next);
        int index = searchkmp(T, S, next);
        StdOut.println("the index is " + index);
    }

    public static void getnext(String s, int[] next) {
        next[0] = -1;
        int j = -1;
        int i = 0;
        while (i < s.length() - 1) {
            if (j == -1 || s.charAt(i) == s.charAt(j)) {
                i ++;
                j ++;
                if (s.charAt(i) != s.charAt(j))
                    next[i] = j;
                else
                    next[i] = next[j];
            } else  
                j = next[j];
        }
    }

    public static int searchkmp(String T, String S, int[] next) {
        int i = 0, j = 0;
        while (i < T.length() && j < S.length()) {
            if (j == -1 || T.charAt(i) == S.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j >= S.length())
            return i - S.length();
        else 
            return -1;
    }
}
