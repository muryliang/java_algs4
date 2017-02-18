import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class copystack {
    public static JavaStack<String> copy(JavaStack<String> itemstack) {
        JavaStack<String> copystack = new JavaStack<String> ();
        JavaStack<String> tmpstack = new JavaStack<String> ();

        for (String s : itemstack)
            tmpstack.push(s);
        for (String s : tmpstack)
            copystack.push(s);
        return copystack;
    }

    public static void main(String[] args) {
        JavaStack<String> origstack = new JavaStack<String>();
        JavaStack<String> copystack;

        while (!StdIn.isEmpty()) 
            origstack.push(StdIn.readString());

        copystack = copy(origstack);

        for (String s : origstack)
            StdOut.print(s + " ");
        StdOut.println();
        for (String s : copystack)
            StdOut.print(s + " ");
        StdOut.println();
    }
}

        

