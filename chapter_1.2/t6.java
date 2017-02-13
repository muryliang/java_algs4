import edu.princeton.cs.algs4.StdOut;

public class t6 { 
    public static void main(String[] args) {
        int flag = 0;
        String base = args[0].concat(args[0]);
        
        if (args[0].length() != args[1].length())
            flag = -1;
        else if (-1 == base.indexOf(args[1]))
            flag = -1;
        else
            flag = 1;
        StdOut.println("flag is " + flag);
    }
}
