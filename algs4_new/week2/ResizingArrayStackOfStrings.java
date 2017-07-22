import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    public void push(String item) {
        if (N == s.length) resize(2*s.length);
        s[N++] = item;
 //       StdOut.println("in push now len "+ s.length+"numbers "+ N);
    }

    public String pop() {
        String item =  s[N];
        s[N--] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
//        StdOut.println("in pop now len "+ s.length+"numbers "+ N);
        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public boolean isEmpty() {
        return N == 0;
    }
    
    public static void main(String[] args) {
        ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-")) StdOut.print(stack.pop());
            else                stack.push(s);
        }
    }
}
