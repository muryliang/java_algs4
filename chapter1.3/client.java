//import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class client {
    public static void main(String[] args) {
        JavaStack<String> cli = new JavaStack<String>();

        if (cli.isEmpty())
            StdOut.println(" empty now ");

        while (!StdIn.isEmpty()) {
            cli.push(StdIn.readString());
       //     StdOut.println("now top is " + cli.top());
        }

        StdOut.println("now have " + cli.size() + " items");
        
        for(String i : cli)
            StdOut.print(i + " ");
        StdOut.println();
    }
}
